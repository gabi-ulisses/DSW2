package edu.ifsp.loja.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.loja.modelo.Produto;

public class ProdutoDAO {

	public List<Produto> findByDescricao(String descricao) throws PersistenceException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		try{
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3307/loja?" +
		        "user=root&password=root");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(
					"SELECT id, descricao, preco FROM produto " + 
					"WHERE descricao LIKE '%" + descricao +"%'"
					);
			
			while (rs.next()) { 
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));

				produtos.add(produto);
			}
			
			rs.close();
			stmt.close();
			conn.close();
				
		}catch(SQLException e){
			throw PersistenceException.wrap(e);
		}
		
		return produtos;
	}
}
