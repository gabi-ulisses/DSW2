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
import jakarta.servlet.http.HttpSession;

@WebServlet("/produto/editar")
public class CadastrarProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Produto produto = new Produto();
        String paramId = request.getParameter("id");
        String paginaDestino;

        if (paramId != null && !paramId.isBlank()) {
            ProdutoDAO dao = DAOFactory.getFactory().getProdutoDAO();
            try {
                int id = Integer.parseInt(paramId);
                produto = dao.findById(id);

                if (produto == null) {
                    response.sendRedirect(request.getContextPath() + "/produto/pesquisar");
                    return;
                }
                paginaDestino = "/paginas/produto/editar.jsp";
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/produto/pesquisar");
                return;
            }
        } else {
            paginaDestino = "/paginas/produto/cadastrar.jsp";
        }

        request.setAttribute("produto", produto);
        RequestDispatcher rd = request.getRequestDispatcher(paginaDestino);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramId = request.getParameter("id");
        String descricao = request.getParameter("descricao");
        String preco = request.getParameter("preco");
        
        String paginaRetorno = (paramId != null && !paramId.isBlank()) 
                               ? "/paginas/produto/editar.jsp" 
                               : "/paginas/produto/cadastrar.jsp";

        ProdutoValidator validator = new ProdutoValidator();
        List<String> errors = validator.validate(descricao, preco);
        
        Produto produto = new Produto();
        
        if (paramId != null && !paramId.isBlank()) {
            try {
                produto.setId(Integer.parseInt(paramId));
            } catch (NumberFormatException e) {
            }
        }
        
        produto.setDescricao(descricao);
        
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("produto", produto); 
            request.getRequestDispatcher(paginaRetorno).forward(request, response);
            return;
        }

        if (paramId == null || paramId.isBlank()) {
             produto.setId(0); 
        }

        try {
            produto.setPreco(Double.parseDouble(preco));
        } catch (NumberFormatException e) {
            errors.add("Erro: Preço inválido. Digite um número.");
            request.setAttribute("errors", errors);
            request.setAttribute("produto", produto); 
            request.getRequestDispatcher(paginaRetorno).forward(request, response);
            return;
        }

        ProdutoDAO dao = DAOFactory.getFactory().getProdutoDAO();
        dao.save(produto); 

        HttpSession session = request.getSession();
        
        if (paramId == null || paramId.isBlank() || produto.getId() == 0) {
            session.setAttribute("mensagemSucesso", "Produto cadastrado com sucesso! ID: " + produto.getId());
        } else {
            session.setAttribute("mensagemSucesso", "Produto ID " + produto.getId() + " atualizado com sucesso.");
        }
        
        response.sendRedirect(request.getContextPath() + "/produto/pesquisar");
    }
}