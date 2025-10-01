package edu.ifsp.loja.persistencia.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.persistencia.DataAccessException;
import edu.ifsp.loja.persistencia.ProdutoDAO;

public class ProdutoDAOMySql implements ProdutoDAO {

	@Override
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
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO produto (descricao, preco) VALUES (?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, produto.getDescricao());
		ps.setDouble(2, produto.getPreco());
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();			
		if (rs.next()) {
			int id = rs.getInt(1);
			produto.setId(id);
		} else {
			throw new DataAccessException("PK faltando");
		}
		
		ps.close();
		conn.close();
	}
	
	private void update(Produto produto) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE produto SET descricao = ?, preco = ? WHERE id = ?");
		ps.setString(1, produto.getDescricao());
		ps.setDouble(2, produto.getPreco());
		ps.setInt(3, produto.getId());
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
}