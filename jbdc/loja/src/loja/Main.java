package loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            /*
             * 1. Conecta ao banco de dados e abre o bloco try-with-resources.
             * A conexão 'conn' será FECHADA AUTOMATICAMENTE no final deste bloco 'try'.
             * Isso garante que os recursos do banco de dados sejam liberados,
             * mesmo que ocorram erros (exceções) no meio do processo.
             */
            try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/loja?" +
                                                         "user=root&password=root")){
            
                // 2. Cria um Statement para enviar comandos SQL.
                Statement stmt = conn.createStatement();
                
                // 3. Simula uma entrada de usuário.
                String inicial = "T";
                
                /*
                 * 4. Executa a consulta, AINDA COM UMA FALHA DE SEGURANÇA.
                 * A variável 'inicial' é concatenada diretamente na query.
                 * Isso permite a INJEÇÃO DE SQL, onde um usuário pode alterar
                 * a lógica da consulta com um texto malicioso.
                 * Esta prática deve ser substituída por PreparedStatement.
                 */
                ResultSet rs = stmt.executeQuery(
                        "SELECT id, nome AS descricao FROM Produto "
                                + "WHERE nome LIKE '" + inicial + "%'" 
                );
                
                // 5. Percorre os resultados.
                while (rs.next()) {
                    int id = rs.getInt("id"); 
                    String nome = rs.getString("descricao");
                    
                    System.out.println(id + " " + nome);
                }
                
                /* 6. Fecha os recursos.
                * rs.close() e stmt.close() precisam ser feitos manualmente
                * pois não estão dentro do bloco try-with-resources.
                * conn.close não é mais necessário.
                */
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}