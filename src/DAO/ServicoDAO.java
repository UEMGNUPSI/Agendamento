/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Model.Servico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dc
 */
public class ServicoDAO {
    
    public void Salvar(Servico servico) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql =  "INSERT INTO serviços SET descricao = ?, area = ?, tempo = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setString(1, servico.getDescricao());
        pst.setString(2, servico.getArea());
        pst.setString(3, servico.getTempo());
        pst.execute();
        pst.close();
    }
    
    public void Alterar(Servico servico) throws SQLException {
        PreparedStatement pst;
        String sql;
        
        sql = "UPDATE serviços set descricao = ?, area = ?, tempo = ? where idserviços = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setString(1, servico.getDescricao());
        pst.setString(2, servico.getArea());
        pst.setString(3, servico.getTempo());
        pst.setInt(4, servico.getId());
        
        pst.execute();
        pst.close();
    }
    
    public void Excluir (Servico servico) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "DELETE FROM serviços where idserviços = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setInt(1, servico.getId());
        
        pst.execute();
        pst.close();
        
    }
    public List<Servico> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Servico> listaservico = new ArrayList<>();
        
        sql = "SELECT idserviços, descricao, area, TIME_FORMAT(tempo, '%h:%i') AS tempo FROM serviços ORDER BY area";
        pst = conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaservico.add(new Servico(
                                rs.getInt("idserviços"),
                                rs.getString("descricao"),
                                rs.getString("area"),
                                rs.getString("tempo")));
        }
        
        pst.close();
        return listaservico;
    }
    
    public List<Servico> BuscarDescricao(String descricao) throws SQLException{
        PreparedStatement pst;
        String sql;
         List<Servico> listaservico = new ArrayList<>();
        String name = "%"+descricao+"%";
        
        sql = "SELECT idserviços, descricao, area, TIME_FORMAT(tempo, '%h:%i') AS tempo FROM serviços where descricao LIKE ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaservico.add(new Servico(
                            rs.getInt("idserviços"),
                            rs.getString("descricao"),
                            rs.getString("area"),
                            rs.getString("tempo")));
        }
        
        pst.close();
        return listaservico;
    }
}
