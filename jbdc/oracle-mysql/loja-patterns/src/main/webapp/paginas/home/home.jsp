<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Loja Patterns</title>
	<base href="<%= request.getContextPath() %>/">
	<link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <div class="cartao">
        <h1>Loja Patterns</h1>
        <h2>Menu</h2>
        <ul class="lista-menu">
            <li>
                <a href="produto/editar">Cadastrar novo produto</a>
            </li>
            
            <li>
                <a href="produto/pesquisar">Pesquisar produtos</a>
            </li>
            <li>
                <a href="cliente/pesquisar">Pesquisar clientes</a>
            </li>
            
        </ul>
    </div>
</body>
</html>