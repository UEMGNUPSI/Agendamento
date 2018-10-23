/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROLLER.conexao;
import Model.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dc
 */
public class LoginDAO {
    
    public Funcionario Loguin(String login, String senha) throws SQLException{
        PreparedStatement pst;
        String sql;
        Funcionario funcionario = null;
        
        sql = "SELECT * FROM funcionario where login = ? AND senha = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, login);
        pst.setString(2, senha);
        
        ResultSet rs = pst.executeQuery();
           while(rs.next()){
               funcionario = new Funcionario(
                            rs.getInt("idfuncionario"),
                            rs.getString("nome"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("nascimento"),
                            rs.getString("telefone1"),
                            rs.getString("telefone2"),
                            rs.getString("email"),
                            rs.getString("endereco"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("cep"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getBoolean("nivel"));
            }
            pst.close();
            return funcionario;
        
    }
}
