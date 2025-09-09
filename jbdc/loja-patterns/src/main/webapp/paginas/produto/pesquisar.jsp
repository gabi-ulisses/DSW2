<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.loja.modelo.Produto" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Produtos - pesquisar</title>
	<base href="<%= request.getContextPath() %>/">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="container mt-4">

	<h1 class="mb-4">Pesquisa de Produtos</h1>
	
	<form action="produto/pesquisar" method="get" class="mb-4">
		<div class="input-group">
			<label for="query" class="input-group-text">Produto:</label>
			<input type="text" id="query" name="query" class="form-control" value="${param.query}">
			<button type="submit" class="btn btn-primary">Pesquisar</button>
		</div>
	</form>

	<%
	List<Produto> produtos = (List<Produto>)request.getAttribute("produtos");
	
	if(produtos != null && !produtos.isEmpty()){ // Se a lista não for nula e não estiver vazia
	%>
	
	<h2 class="mb-3">Resultados para "<%= request.getParameter("query") %>"</h2>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Descrição</th>
				<th scope="col">Preço</th>
			</tr>
		</thead>
		<tbody>
			<% for (Produto p : produtos) { %>
			<tr>
				<td><%= p.getId() %></td>
				<td><%= p.getDescricao() %></td>
				<td>R$ <%= String.format("%.2f", p.getPreco()) %></td>
			</tr>
			<% } %>
		</tbody>
	</table>

	<%
		} else { // Se a lista estiver vazia
	%>	
	<p class="alert alert-info">A consulta não retornou resultados.</p>
	<%
		}
	%>
	
	<hr class="my-4">
	<a href="home" class="btn btn-secondary">Voltar para a Home</a>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>