package edu.ifsp.loja.persistencia;

import java.util.List;
import edu.ifsp.loja.modelo.Produto;

public interface ProdutoDAO {
	Produto save(Produto produto);
	Produto findById(int id);
	List<Produto> findByDescricao(String descricao);
}