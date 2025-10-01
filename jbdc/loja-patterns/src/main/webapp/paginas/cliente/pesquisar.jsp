<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.loja.modelo.Cliente" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Clientes - Pesquisar</title>
	<base href="<%= request.getContextPath() %>/">
	<link rel="stylesheet" href="css/estilo.css">
</head>
<body>
<div class="container">
	<h1>Pesquisa de Clientes</h1>
	
	<div style="margin-bottom: 20px;">
		<label for="tipoDeBusca" style="display: block; margin-bottom: 5px;">Selecione o tipo de busca:</label>
		<select id="tipoDeBusca">
			<option value="">Selecione...</option>
			<option value="nome">Buscar por Nome</option>
			<option value="codigo">Buscar por Código</option>
		</select>
	</div>

	<div id="formNome" style="display: none;">
		<form action="cliente/pesquisar" method="get">
			<div class="grupo-formulario">
				<input type="text" name="name" value="${param.queryNome}" placeholder="Digite parte do nome do cliente...">
				<button type="submit" class="botao">Buscar</button>
			</div>
		</form>
	</div>

	<div id="formCodigo" style="display: none;">
		<form action="cliente/pesquisar" method="get">
			<div class="grupo-formulario">
				<input type="number" name="id" value="${param.queryId}" placeholder="Digite o código exato do cliente...">
				<button type="submit" class="botao">Buscar</button>
			</div>
		</form>
	</div>

	<%
	List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
	Cliente cliente = (Cliente) request.getAttribute("cliente");
	String erro = (String) request.getAttribute("erro");
	
	if (erro != null) {
	%>
		<p class="alerta"><%= erro %></p>
	<%
	}
	
	if (clientes != null) {
		if (!clientes.isEmpty()) {
	%>
		<h2>Resultados para a busca por nome: "${param.queryNome}"</h2>
		<div class="container-tabela">
			<table class="tabela-resultados">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>E-mail</th>
					</tr>
				</thead>
				<tbody>
					<% for (Cliente c : clientes) { %>
					<tr>
						<td><%= c.getId() %></td>
						<td><%= c.getNome() %></td>
						<td><%= c.getEmail() %></td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>
	<%
		} else { 
	%>	
		<p class="alerta">Nenhum cliente encontrado para o nome informado.</p>
	<%
		}
	} 
	else if (request.getParameter("queryId") != null) {
		if (cliente != null) {
	%>
		<h2>Resultado para a busca por código: "${param.queryId}"</h2>
		<div class="container-tabela">
			<table class="tabela-resultados">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>E-mail</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%= cliente.getId() %></td>
						<td><%= cliente.getNome() %></td>
						<td><%= cliente.getEmail() %></td>
					</tr>
				</tbody>
			</table>
		</div>
	<%
		} else {
	%>
		<p class="alerta">Nenhum cliente encontrado para o código informado.</p>
	<%
		}
	}
	%>
	
	<hr style="margin-top: 30px; border: none; border-top: 1px solid #eee;">
	<a href="home" class="botao-voltar">Voltar para a Home</a>

	<script>
		const tipoDeBusca = document.getElementById('tipoDeBusca');
		const formNome = document.getElementById('formNome');
		const formCodigo = document.getElementById('formCodigo');
		
		tipoDeBusca.addEventListener('change', function() {
			const buscaSelecionada = this.value;
			
			formNome.style.display = 'none';
			formCodigo.style.display = 'none';
			
			if (buscaSelecionada === 'nome') {
				formNome.style.display = 'block';
			} else if (buscaSelecionada === 'codigo') {
				formCodigo.style.display = 'block';
			}
		});
	</script>
</div>
</body>
</html>