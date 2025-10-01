package edu.ifsp.loja.persistencia.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.loja.modelo.Cliente;
import edu.ifsp.loja.persistencia.ClienteDAO;
import edu.ifsp.loja.persistencia.DataAccessException;

public class ClienteDAOMySql implements ClienteDAO {
	
	@Override
	public List<Cliente> findByName(String name) {
		List<Cliente> list = new ArrayList<>();
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT id, nome, email FROM cliente WHERE nome LIKE ?");
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = mapRow(rs);
				list.add(cliente);
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		
		return list;
	}
	
	@Override
	public Cliente findById(int id) {
		Cliente cliente = null;
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT id, nome, email FROM cliente WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				cliente = mapRow(rs);
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		
		return cliente;
	}

	private Cliente mapRow(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId(rs.getInt("id"));
		cliente.setNome(rs.getString("nome"));
		cliente.setEmail(rs.getString("email"));
		return cliente;
	}
}
