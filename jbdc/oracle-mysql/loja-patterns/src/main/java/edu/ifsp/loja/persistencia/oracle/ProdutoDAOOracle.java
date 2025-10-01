package edu.ifsp.loja.persistencia.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.persistencia.DataAccessException;
import edu.ifsp.loja.persistencia.ProdutoDAO;

public class ProdutoDAOOracle implements ProdutoDAO {

	@Override
	public List<Produto> findByDescricao(String descricao) {
		List<Produto> produtos = new ArrayList<>();
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT id, descricao, preco FROM produto WHERE UPPER(descricao) LIKE UPPER(?)");
			ps.setString(1, "%" + descricao + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produtos.add(produto);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return produtos;
	}
	
	@Override
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
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return produto;		
	}
	
	@Override
	public Produto save(Produto produto) {
		try {
			if (produto.getId() == 0) {
				insert(produto);
			} else {
				update(produto);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return produto;
	}

	private void insert(Produto produto) throws SQLException {
		String sql = "INSERT INTO produto (id, descricao, preco) VALUES (seq_produto.NEXTVAL, ?, ?)";
		Connection conn = ConnectionFactory.getConnection();
		try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
			ps.setString(1, produto.getDescricao());
			ps.setDouble(2, produto.getPreco());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					produto.setId(rs.getInt(1));
				} else {
					throw new SQLException("Falha ao obter o ID gerado para o produto.");
				}
			}
		}
		conn.close();
	}

	private void update(Produto produto) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		try (PreparedStatement ps = conn.prepareStatement("UPDATE produto SET descricao = ?, preco = ? WHERE id = ?")) {
			ps.setString(1, produto.getDescricao());	
			ps.setDouble(2, produto.getPreco());
			ps.setInt(3, produto.getId());
			ps.executeUpdate();
		}
		conn.close();
	}
}