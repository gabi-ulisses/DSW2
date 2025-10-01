package edu.ifsp.loja.persistencia.oracle;

import edu.ifsp.loja.persistencia.ClienteDAO;
import edu.ifsp.loja.persistencia.DAOFactory;
import edu.ifsp.loja.persistencia.ProdutoDAO;

public class OracleDAOFactory extends DAOFactory {

    @Override
    public ProdutoDAO getProdutoDAO() {
        return new ProdutoDAOOracle();
    }

	@Override
	public ClienteDAO getClienteDAO() {
		return new ClienteDAOOracle();
	}

}