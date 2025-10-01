<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.loja.modelo.Produto" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Produtos - Pesquisar</title>
	<base href="<%= request.getContextPath() %>/">
	<link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <div class="container">
        <h1>Pesquisa de Produtos</h1>
        <%
 
		    String mensagemSucesso = (String) session.getAttribute("mensagemSucesso");
		    if (mensagemSucesso != null) {
		        // Exibe a mensagem em uma div de sucesso
		    %>
		        <div class="alerta sucesso" style="margin-bottom: 20px;">
		            <%= mensagemSucesso %>
		        </div>
		    <%
		        // Remove a mensagem da sessão para que ela não apareça novamente
		        session.removeAttribute("mensagemSucesso");
		    }
		  %>
        <form action="produto/pesquisar" method="get">
            <div class="grupo-formulario">
                <input type="text" name="query" value="${param.query}" placeholder="Digite o nome do produto...">
                <button type="submit" class="botao">Pesquisar</button>
            </div>
        </form>

        <%
        List<Produto> produtos = (List<Produto>)request.getAttribute("produtos");
        if(produtos != null) {
            if (!produtos.isEmpty()) {
        %>
        
        <h2>Resultados para "<%= request.getParameter("query") %>"</h2>
        <div class="container-tabela">
            <table class="tabela-resultados">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Descrição</th>
                        <th>Preço</th>
                        <th class="acoes-coluna">Ações</th> </tr>
                </thead>
                <tbody>
                    <% for (Produto p : produtos) { %>
                    <tr id="produtoRow_<%= p.getId() %>">
                        <td><%= p.getId() %></td>
                        <td data-campo="descricao"><%= p.getDescricao() %></td>
                        <td data-campo="preco">R$ <%= String.format("%.2f", p.getPreco()) %></td>
                        <td class="acoes-coluna">
                            <button type="button" class="botao" onclick="habilitarEdicao(<%= p.getId() %>)">Editar</button>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <%
            } else {
        %>	
        <p class="alerta">A consulta não retornou resultados.</p>
        <%
            }
        }
        %>
        
        <hr style="margin-top: 30px; border: none; border-top: 1px solid #eee;">
        <a href="home" class="botao-voltar">Voltar para a Home</a>
    </div>

    <script>

    function habilitarEdicao(produtoId) {
            const linha = document.getElementById('produtoRow_' + produtoId);
            
            if (linha.querySelector('form')) {
                return;
            }

            const tdDescricao = linha.querySelector('td[data-campo="descricao"]');
            const tdPreco = linha.querySelector('td[data-campo="preco"]');
            const precoAtual = tdPreco.innerText.replace('R$ ', '').replace(',', '.').trim(); // Limpa e prepara o preço
            const descricaoAtual = tdDescricao.innerText.trim();

            const formEdicao = document.createElement('form');
            formEdicao.action = 'produto/editar';
            formEdicao.method = 'post';
            formEdicao.onsubmit = function(event) {
                return true; 
            };

            const inputDescricao = document.createElement('input');
            inputDescricao.type = 'text';
            inputDescricao.name = 'descricao';
            inputDescricao.value = descricaoAtual;
            inputDescricao.required = true;

            const inputPreco = document.createElement('input');
            inputPreco.type = 'number';
            inputPreco.name = 'preco';
            inputPreco.step = '0.01'; 
            inputPreco.value = precoAtual;
            inputPreco.required = true;
            
            const inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'id';
            inputId.value = produtoId;
            
            const btnSalvar = document.createElement('button');
            btnSalvar.type = 'submit';
            btnSalvar.className = 'botao';
            btnSalvar.innerText = 'Salvar';
            
            const btnCancelar = document.createElement('button');
            btnCancelar.type = 'button';
            btnCancelar.className = 'botao-cancelar'; 
            btnCancelar.innerText = 'Cancelar';
            btnCancelar.onclick = () => desabilitarEdicao(produtoId, descricaoAtual, tdPreco.innerText);

                        tdDescricao.innerHTML = '';
            tdDescricao.appendChild(inputDescricao);
            
            // Limpa e insere o inputPreco
            tdPreco.innerHTML = '';
            tdPreco.appendChild(inputPreco);
            
            // Limpa a coluna de Ações e insere o formulário de botões
            const tdAcoes = linha.querySelector('.acoes-coluna');
            tdAcoes.innerHTML = '';

            const grupoBotoes = document.createElement('div');
            grupoBotoes.className = 'grupo-formulario-edicao';
            grupoBotoes.appendChild(btnSalvar);
            grupoBotoes.appendChild(btnCancelar);

            formEdicao.appendChild(inputId); // Adiciona o ID ao formulário
            formEdicao.appendChild(grupoBotoes);
            tdAcoes.appendChild(formEdicao);

        }

        // Função para reverter a linha da tabela para o estado de visualização
        function desabilitarEdicao(produtoId, descricaoOriginal, precoOriginal) {
            const linha = document.getElementById('produtoRow_' + produtoId);
            const tdDescricao = linha.querySelector('td[data-campo="descricao"]');
            const tdPreco = linha.querySelector('td[data-campo="preco"]');
            const tdAcoes = linha.querySelector('.acoes-coluna');
            
            // Restaura o conteúdo original das células
            tdDescricao.innerHTML = descricaoOriginal;
            tdPreco.innerHTML = precoOriginal;
            
            // Restaura o botão de edição
            tdAcoes.innerHTML = `<button type="button" class="botao" onclick="habilitarEdicao(${produtoId})">Editar</button>`;
        }
    </script>
</body>
</html>