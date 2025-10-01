package edu.ifsp.loja.persistencia;

import java.io.InputStream;
import java.util.Properties;

public abstract class DAOFactory {
    private static DAOFactory instance;

    public abstract ProdutoDAO getProdutoDAO();
    public abstract ClienteDAO getClienteDAO();

    public static DAOFactory getFactory() {
        if (instance == null) {
            try {
                Properties props = new Properties();
                InputStream input = DAOFactory.class.getClassLoader().getResourceAsStream("config.properties");
                if (input == null) {
                    throw new IllegalStateException("Arquivo config.properties não encontrado no classpath.");
                }
                props.load(input);
                String factoryClassName = props.getProperty("dao.factory.class");
                instance = (DAOFactory) Class.forName(factoryClassName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Não foi possível carregar a DAOFactory", e);
            }
        }
        return instance;
    }
}