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
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="container mt-4">

	<h1 class="mb-4">Pesquisa de Clientes</h1>
	
	<div class="card mb-4">
		<div class="card-header">
			Pesquisar por Nome
		</div>
		<div class="card-body">
			<form action="cliente/pesquisar" method="get">
				<div class="input-group">
					<label for="queryNome" class="input-group-text">Nome do Cliente:</label>
					<input type="text" id="queryNome" name="queryNome" class="form-control" value="${param.queryNome}" placeholder="Digite parte do nome...">
					<button type="submit" class="btn btn-primary">Pesquisar</button>
				</div>
			</form>
		</div>
	</div>

	<div class="card mb-4">
		<div class="card-header">
			Pesquisar por Código
		</div>
		<div class="card-body">
			<form action="cliente/pesquisar" method="get">
				<div class="input-group">
					<label for="queryId" class="input-group-text">Código do Cliente:</label>
					<input type="number" id="queryId" name="queryId" class="form-control" value="${param.queryId}" placeholder="Digite o código exato...">
					<button type="submit" class="btn btn-primary">Pesquisar</button>
				</div>
			</form>
		</div>
	</div>
	
	<hr>

	<%
	// Recupera os atributos enviados pelo Servlet
	List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
	Cliente cliente = (Cliente) request.getAttribute("cliente");
	String erro = (String) request.getAttribute("erro");
	
	// Exibe mensagem de erro, se houver (ex: ID inválido)
	if (erro != null) {
	%>
		<div class="alert alert-danger"><%= erro %></div>
	<%
	}
	
	// --- Lógica para exibir resultados da busca por NOME ---
	if (clientes != null) {
		if (!clientes.isEmpty()) {
	%>
		<h2 class="mb-3">Resultados para a busca por nome: "${param.queryNome}"</h2>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nome</th>
					<th scope="col">E-mail</th>
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
	<%
		} else { // Se a lista estiver vazia
	%>	
		<div class="alert alert-info">Nenhum cliente encontrado para o nome informado.</div>
	<%
		}
	} 
	// --- Lógica para exibir resultados da busca por CÓDIGO ---
	else if (request.getParameter("queryId") != null) {
		if (cliente != null) {
	%>
		<h2 class="mb-3">Resultado para a busca por código: "${param.queryId}"</h2>
		<div class="card" style="width: 24rem;">
			<div class="card-body">
				<h5 class="card-title"><%= cliente.getNome() %></h5>
				<h6 class="card-subtitle mb-2 text-muted">ID: <%= cliente.getId() %></h6>
				<p class="card-text"><strong>E-mail:</strong> <%= cliente.getEmail() %></p>
			</div>
		</div>
	<%
		} else { // Se o cliente for nulo (não encontrado)
	%>
		<div class="alert alert-info">Nenhum cliente encontrado para o código informado.</div>
	<%
		}
	}
	%>
	
	<hr class="my-4">
	<a href="home" class="btn btn-secondary">Voltar para a Home</a>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
