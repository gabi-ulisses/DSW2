package edu.ifsp.loja.persistencia;

import java.util.List;

import edu.ifsp.loja.modelo.Cliente;

public interface ClienteDAO {
	List<Cliente> findByName(String name);
	Cliente findById(int id);
}
