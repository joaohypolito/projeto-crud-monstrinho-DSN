/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author João
 */
public class DAOConnection {
    
    private static Connection conexao;
    
    static {
        if (conexao == null) {
            conexao = obtemConexao();
        }
        
    }
    
    // Método
    private static Connection obtemConexao() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver conector
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        
        try {
            String url = "jdbc:mysql://localhost:3306/banco_teste"; // String de conexão do banco
            String user = "root"; // Usuário para conexão
            String pwd = ""; // Senha para conexão
            
            return DriverManager.getConnection(url, user, pwd);
        } catch (SQLException sqle) {
            
            sqle.printStackTrace();
            
            return null;
            
        }
    }
    
    // Método
    private static void encerraConexao() {
        
        try {
            if ((conexao != null) && (!conexao.isClosed())) {
                conexao.close();
                conexao = null;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public static Connection getInstance() {
        return conexao;
    }
    
}