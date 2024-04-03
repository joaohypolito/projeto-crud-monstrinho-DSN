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

    public boolean incluirAluno(CadCliVO vo) {
        boolean retorno = false;

        // Instanciando a conexão
        Connection conexao = DAOConnection.getInstance();

        // Se a conexão não for nula
        if (conexao != null) {
            try {
                // Query SQL
                String sql = "INSERT INTO clientes (codigo, nome, endereco, rg, cpf, dtnasc, celular, telfixo, email, responsavel, celularresponsavel, modalidade, sexo, periodo, horario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conexao.prepareStatement(sql);

                // Setando os valores dos parâmetros
                pstmt.setString(1, vo.getCodigo());
                pstmt.setString(2, vo.getNomeAluno());
                pstmt.setString(3, vo.getEndereco());
                pstmt.setString(4, vo.getRg());
                pstmt.setString(5, vo.getCpf());
                pstmt.setString(6, vo.getDtnasc());
                pstmt.setString(7, vo.getCelular());
                pstmt.setString(8, vo.getTelFixo());
                pstmt.setString(9, vo.getEmail());
                pstmt.setString(10, vo.getResponsavel());
                pstmt.setString(11, vo.getCelularResponsavel());
                pstmt.setString(12, vo.getModalidade());
                pstmt.setString(13, vo.getSexo());
                pstmt.setString(14, vo.getPeriodo());
                pstmt.setObject(15, vo.getHorario());

                // Executando a Query
                retorno = pstmt.executeUpdate() > 0;
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
                retorno = criaColecaoAluno(pstmt.executeQuery());
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        
        return retorno;
    }

    public Collection<CadCliVO> criaColecaoAluno(ResultSet cliente) {
        ArrayList<CadCliVO> retorno = new ArrayList<>();

        try {
            while (cliente.next()) {
                CadCliVO cli = new CadCliVO();
                cli.setCodigo(cliente.getString("codigo"));
                cli.setNomeAluno(cliente.getString("nomeAluno"));
                cli.setRg(cliente.getString("rg"));
                cli.setCpf(cliente.getString("cpf"));
                cli.setEndereco(cliente.getString("endereco"));
                cli.setDtnasc(cliente.getString("dtnasc"));
                cli.setCelular(cliente.getString("celular"));
                cli.setTelFixo(cliente.getString("telFixo"));
                cli.setEmail(cliente.getString("email"));
                cli.setResponsavel(cliente.getString("responsavel"));
                cli.setCelularResponsavel(cliente.getString("celularResponsavel"));
                cli.setModalidade(cliente.getString("modalidade"));
                cli.setSexo(cliente.getString("sexo"));
                cli.setPeriodo(cliente.getString("periodo"));
                cli.setHorario(cliente.getTime("horario").toLocalTime());

                retorno.add(cli);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return retorno;
    }

    public int atualizaCliente(CadCliVO vo) {
        int retorno = 0;
        Connection conexao = DAOConnection.getInstance();

        if (conexao != null) {
            try {
                PreparedStatement pstmt = conexao.prepareStatement("UPDATE clientes SET nome=?, endereco=?, rg=?, cpf=?, dtnasc=?, celular=?, telfixo=?, email=?, responsavel=?, celularresponsavel=?, modalidade=?, sexo=?, periodo=?, horario=? WHERE codigo=?");

                pstmt.setString(1, vo.getNomeAluno());
                pstmt.setString(2, vo.getEndereco());
                pstmt.setString(3, vo.getRg());
                pstmt.setString(4, vo.getCpf());
                pstmt.setString(5, vo.getDtnasc());
                pstmt.setString(6, vo.getCelular());
                pstmt.setString(7, vo.getTelFixo());
                pstmt.setString(8, vo.getEmail());
                pstmt.setString(9, vo.getResponsavel());
                pstmt.setString(10, vo.getCelularResponsavel());
                pstmt.setString(11, vo.getModalidade());
                pstmt.setString(12, vo.getSexo());
                pstmt.setString(13, vo.getPeriodo());
                pstmt.setObject(14, vo.getHorario());
                pstmt.setString(15, vo.getCodigo());

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
