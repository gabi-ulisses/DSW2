<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
final String homeUrl = request.getContextPath() + "/home";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Loja Patterns</title>
	<meta http-equiv="refresh" content="0;<%= homeUrl %>">
</head>
<body>
	<p>Redirecionando para a <a href="<%= homeUrl %>">página principal</a>...</p>
</body>
</html>