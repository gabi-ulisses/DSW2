package edu.ifsp.loja.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.loja.modelo.Produto;

public class ProdutoDAO {
	
	
	public List<Produto> findByDescricao(String descricao) {
		List<Produto> produtos = new ArrayList<>();

		try {
			Connection conn = ConnectionFactory.getConnection();

			PreparedStatement ps = conn.prepareStatement("SELECT id, descricao, preco FROM produto WHERE descricao LIKE ?");
			ps.setString(1, "%" + descricao + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));

				produtos.add(produto);
			}

		} catch (SQLException e) {
			throw new DataAccessException(e);
		}

		return produtos;
	}

	
	public Produto findById(int id) {
		Produto produto = null;
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT id, descricao, preco FROM produto WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));				
			}
			
			conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	
		return produto;		
	}
}
