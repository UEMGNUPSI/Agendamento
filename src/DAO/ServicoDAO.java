/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import CONTROLLER.conexao;
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
        
        sql =  "INSERT INTO serviços SET descricao = ?, idarea = ?, tempo = ?,ativo = 0";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setString(1, servico.getDescricao());
        pst.setInt(2, servico.getIdArea());
        pst.setString(3, servico.getTempo());
        pst.execute();
        pst.close();
    }
    
    public void Alterar(Servico servico) throws SQLException {
        PreparedStatement pst;
        String sql;
        
        sql = "UPDATE serviços set descricao = ?, idarea = ?, tempo = ? where idserviços = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setString(1, servico.getDescricao());
        pst.setInt(2, servico.getIdArea());
        pst.setString(3, servico.getTempo());
        pst.setInt(4, servico.getId());
        
        pst.execute();
        pst.close();
    }
    
    public void Excluir (Servico servico) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "UPDATE serviços set ativo = 1 where idserviços = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setInt(1, servico.getId());
        
        pst.execute();
        pst.close();
        
    }
    public List<Servico> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Servico> listaservico = new ArrayList<>();
        
        sql = "SELECT idserviços, descricao, idarea, TIME_FORMAT(tempo, '%H:%i') AS tempo FROM serviços WHERE ativo = 0 ORDER BY idarea";
        pst = conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaservico.add(new Servico(
                                rs.getInt("idserviços"),
                                rs.getString("descricao"),
                                rs.getString("tempo"),
                                rs.getInt("idarea")));
        }
        
        pst.close();
        return listaservico;
    }
    
    public List<Servico> BuscarDescricao(String descricao) throws SQLException{
        PreparedStatement pst;
        String sql;
         List<Servico> listaservico = new ArrayList<>();
        String name = "%"+descricao+"%";
        
        sql = "SELECT idserviços, descricao, idarea, TIME_FORMAT(tempo, '%H:%i') AS tempo FROM serviços where descricao LIKE ? AND ativo = 0";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaservico.add(new Servico(
                            rs.getInt("idserviços"),
                            rs.getString("descricao"),
                            rs.getString("tempo"),
                            rs.getInt("idarea")));
        }
        
        pst.close();
        return listaservico;
    }
    
    public Servico Buscar(Integer id) throws SQLException{
        PreparedStatement pst;
        String sql;   
        Servico servico = null;
        
        sql ="SELECT idserviços, descricao, idarea , TIME_FORMAT(tempo, '%H:%i') AS tempo FROM serviços where idserviços = ?";
                
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        
            while(rs.next()){
            servico = new Servico(
                            rs.getInt("idserviços"),
                            rs.getString("descricao"),
                            rs.getString("tempo"),
                            rs.getInt("idarea"));
        }
        
            
        return servico;
        
    }
}
