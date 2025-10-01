<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.loja.modelo.Produto" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Produtos - Cadastrar</title>
	<base href="<%= request.getContextPath() %>/">
	<link rel="stylesheet" href="css/estilo.css">
</head>
<body>
<div class="container">
	<h1>Cadastro de Novo Produto</h1>
	
	<%
	// Tratamento de Erros
	List<String> errors = (List<String>)request.getAttribute("errors");
	if (errors != null && !errors.isEmpty()) {
	%>
	<ul class="alerta">
		<% for (String erro : errors) { %>
			<li><%= erro %></li>
		<% } %>
	</ul>	
	<%
	}
	
	// Objeto Produto para repopular campos em caso de erro
	Produto produto = (Produto)request.getAttribute("produto");
	%>
	
	<form action="produto/editar" method="post">		
		
		<div class="grupo-formulario">
			<label for="descricao">Descrição: </label>
			<input type="text" name="descricao" id="descricao" required placeholder="Ex: Notebook Gamer"
			       value="<%= produto != null ? produto.getDescricao() : "" %>">
		</div>

		<div class="grupo-formulario">
			<label for="preco">Preço (R$): </label>
			<input type="number" name="preco" id="preco" required step="0.01" placeholder="Ex: 2500.50" value="<%= produto != null && produto.getPreco() != 0.0 ? produto.getPreco() : "" %>">
		</div>
		
		<div class="grupo-formulario">
			<button type="submit" class="botao">Cadastrar</button>
		</div>
	</form>
	
	<hr style="margin-top: 30px; border: none; border-top: 1px solid #eee;">
	<a href="home" class="botao-voltar">Voltar para a Home</a>
</div>
</body>
</html>