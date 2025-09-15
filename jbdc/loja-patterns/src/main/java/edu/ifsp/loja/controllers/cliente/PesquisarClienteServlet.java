package edu.ifsp.loja.controllers.cliente;

import java.io.IOException;
import java.util.List;

import edu.ifsp.loja.modelo.Cliente;
import edu.ifsp.loja.persistencia.ClienteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Mapeia o servlet para a URL "/cliente/pesquisar"
@WebServlet("/cliente/pesquisar")
public class PesquisarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Pega os parâmetros do formulário da página JSP.
		String nomeQuery = request.getParameter("queryNome");
		String idQuery = request.getParameter("queryId");
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		// Verifica qual tipo de busca deve ser feita[cite: 9].
		if (nomeQuery != null && !nomeQuery.isEmpty()) {
			// Se o parâmetro "queryNome" foi enviado, faz a busca por nome[cite: 11].
			List<Cliente> clientes = clienteDAO.findByNome(nomeQuery);
			
			// Adiciona a lista de resultados como um atributo na requisição.
			request.setAttribute("clientes", clientes);
			
		} else if (idQuery != null && !idQuery.isEmpty()) {
			// Se o parâmetro "queryId" foi enviado, faz a busca por código[cite: 10, 14].
			try {
				int id = Integer.parseInt(idQuery);
				Cliente cliente = clienteDAO.findById(id);
				
				// Adiciona o cliente encontrado (ou null) como um atributo na requisição.
				request.setAttribute("cliente", cliente);
				
			} catch (NumberFormatException e) {
				// Caso o ID informado não seja um número válido, podemos adicionar uma mensagem de erro.
				request.setAttribute("erro", "O código informado é inválido.");
			}
		}
		
		// Encaminha a requisição (com os atributos de resultado) para a página JSP.
		RequestDispatcher rd = request.getRequestDispatcher("/paginas/cliente/pesquisar.jsp");
		rd.forward(request, response);
	}
}
