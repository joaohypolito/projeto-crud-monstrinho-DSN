/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.DAOConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import vo.CadCliVO;

/**
 *
 * @author João
 */
public class CadCliDAO {
    
    public boolean incluirClientes(CadCliVO vo) {
        
        boolean retorno = false;
        
        // Instanciando a conexão
        Connection conexao = DAOConnection.getInstance();
        
        // Se a conexão não for nula
        if (conexao != null) {
            
            try {
            // Query SQL
            PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO clientes (nome, endereco, rg, cpf, dtnasc, celular, email, contato, celularcontato) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");

            // Obtendo dados do controller
            pstmt.setString(1, vo.getNome());
            pstmt.setString(2, vo.getEndereco());
            pstmt.setString(3, vo.getRg());
            pstmt.setString(4, vo.getCpf());
            pstmt.setString(5, vo.getDtnasc());
            pstmt.setString(6, vo.getCelular());
            pstmt.setString(7, vo.getEmail());
            pstmt.setString(8, vo.getContato());
            pstmt.setString(9, vo.getCelularcontato());

            // Executando a Query
            retorno = pstmt.execute();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        }
        
        return retorno;
        
    }
    
    // Método
    // Recebe o comando e o valor passado pelo CadCli
    public Collection<CadCliVO> selectCadCli(String comando, String valor) { 
        Collection<CadCliVO> retorno = null;
        
        Connection conexao = DAOConnection.getInstance();
        
        if(conexao!=null) {
            try {
                PreparedStatement pstmt = conexao.prepareStatement ("SELECT * FROM clientes WHERE " +comando+ "=?"); // Query SQL
                pstmt.setString(1, valor);
                retorno = criaColecaoCliente(pstmt.executeQuery());
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        
        return retorno;
    }
    
    public Collection<CadCliVO> criaColecaoCliente(ResultSet cliente) {
        ArrayList<CadCliVO> retorno = new ArrayList<CadCliVO>();
        
        try {
            while(cliente.next()) {
                CadCliVO cli = new CadCliVO();
                cli.setCodigo(cliente.getString(1));
                cli.setNome(cliente.getString(2));
                cli.setEndereco(cliente.getString(3));
                cli.setRg(cliente.getString(4));
                cli.setCpf(cliente.getString(5));
                cli.setDtnasc(cliente.getString(6));
                cli.setCelular(cliente.getString(7));
                cli.setEmail(cliente.getString(8));
                cli.setContato(cliente.getString(9));
                cli.setCelularcontato(cliente.getString(10));
                
                retorno.add(cli);
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return retorno;
    }
    
    public int atualizaCliente(CadCliVO vo) {
        
        int retorno = 0;
        
        Connection conexao = DAOConnection.getInstance();
        
        if(conexao!=null) {
            try {
                PreparedStatement pstmt = conexao.prepareStatement("UPDATE clientes SET nome=?, endereco=?, rg=?, cpf=?, dtnasc=?, celular=?, email=?, contato=?, celularcontato=? WHERE codigo=?");
                
                pstmt.setString(1,vo.getNome());
                pstmt.setString(2, vo.getEndereco());
                pstmt.setString(3, vo.getRg());
                pstmt.setString(4, vo.getCpf());
                pstmt.setString(5, vo.getDtnasc());
                pstmt.setString(6, vo.getCelular());
                pstmt.setString(7, vo.getEmail());
                pstmt.setString(8, vo.getContato());
                pstmt.setString(9, vo.getCelularcontato());
                pstmt.setString(4, vo.getCodigo());
                
                retorno = pstmt.executeUpdate();
                
            } catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        
        return retorno;
        
    }
    
    public boolean deleteCliente(CadCliVO vo) {
        
        boolean retorno = false;
        
        // Instanciando a classe de conexão para utilizar
        Connection conexao = DAOConnection.getInstance();
        
        // Se a conexão não for nula
        if(conexao != null) {
            try {
                PreparedStatement pstmt = conexao.prepareStatement ("DELETE FROM clientes WHERE codigo=?");
                pstmt.setString(1, vo.getCodigo());
                retorno = pstmt.execute();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        
        return retorno;
    }
    
}
