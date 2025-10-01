package edu.ifsp.loja.web.produto;

import java.io.IOException;
import java.util.List;
import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.persistencia.DAOFactory;
import edu.ifsp.loja.persistencia.ProdutoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/produto/pesquisar")
public class PesquisarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String query = request.getParameter("query");

		if (query != null) {
			ProdutoDAO dao = DAOFactory.getFactory().getProdutoDAO();
			List<Produto> produtos = dao.findByDescricao(query);
			request.setAttribute("produtos", produtos);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/paginas/produto/pesquisar.jsp");
		rd.forward(request, response);
	}
}