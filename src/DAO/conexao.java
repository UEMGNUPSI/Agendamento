/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dc
 */
public class conexao {
    
   private static conexao conexao = null;
    private static Connection connection;
    private String usuario;
    private String senha;
    private String url;
    
    public conexao() throws SQLException
    {
        usuario = "root";
        senha = "";
        url = "jdbc:mysql://localhost:3306/pratileira?autoReconnect=true&useSSL=false";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, usuario, senha);
            
        }catch (ClassNotFoundException | SQLException e){
    }
    }
    public static Connection getInstance() throws SQLException
{
    if(connection == null){
        synchronized(conexao.class){
            conexao = new conexao();
        }
    }
    return connection;
}
    public static void closeInstance() throws SQLException
    {
        if (connection != null){
            connection.close();
        }
    }
    
}
