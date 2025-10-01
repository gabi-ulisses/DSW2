<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Loja Patterns</title>
	<base href="<%= request.getContextPath() %>/">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="container mt-5">
	<div class="card p-4 mx-auto" style="max-width: 500px;">
		<h1 class="card-title text-center mb-4">Loja Patterns</h1>
		
		<div class="card-body">
			<h2 class="card-subtitle mb-3">Menu</h2>
			<ul class="list-group list-group-flush">
				<li class="list-group-item">
					<a href="produto/pesquisar" class="text-decoration-none d-block">Pesquisar produtos</a>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>