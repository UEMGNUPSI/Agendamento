/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
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
        try {
            String currentDir = System.getProperty("user.dir");
            Path c = Paths.get(currentDir + "/conexao.txt");
            List<String> texto = Files.readAllLines(c, Charset.defaultCharset());

            int tamanho0 = texto.get(0).length();
            int tamanho1 = texto.get(1).length();
            int tamanho2 = texto.get(2).length();
            int i0 = texto.get(0).indexOf('"');
            int i1 = texto.get(1).indexOf('"');
            int i2 = texto.get(2).indexOf('"');
            String r0 = texto.get(0).substring(i0 + 1, tamanho0);
            String r1 = texto.get(1).substring(i1 + 1, tamanho1);
            String r2 = texto.get(2).substring(i2 + 1, tamanho2);
            int i02 = r0.indexOf('"');
            int i12 = r1.indexOf('"');
            int i22 = r2.indexOf('"');
            usuario = r0.substring(0, i02);
            senha = r1.substring(0, i12);
            url = r2.substring(0, i22);

        } catch (Exception erro) {
            erro.printStackTrace();
        }
        
//        usuario = "agendamento";
//        senha = "Uemg2018";
//        url = "jdbc:mysql://10.93.10.10:3306/pratileira?autoReconnect=true&useSSL=false";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, usuario, senha);
            
        }catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Sem conexão com o Banco de Dados!\nEntre em contato com o Administrador do sistema!","Erro",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
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
