/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROLLER.conexao;
import Model.Area;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dc
 */
public class AreaDAO {
    
     public void Salvar(Area area) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql =  "INSERT INTO area SET nome = ?, ativo = 0";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setString(1, area.getNome());
        
        pst.execute();
        pst.close();
    }
    
     public void Alterar(Area area) throws SQLException {
        PreparedStatement pst;
        String sql;
        
        sql =  "UPDATE area SET nome = ? where idarea = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setString(1, area.getNome());
        pst.setInt(2, area.getId());
        
        pst.execute();
        pst.close();
         
     }
     
    public void Excluir(Area area) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "UPDATE area SET ativo = 1 WHERE idarea = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, area.getId());
        
        pst.execute();
        pst.close();
    }
     
   public List<Area> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Area> listaArea = new ArrayList<>();
        
        sql = "SELECT * FROM area WHERE ativo = 0 ORDER BY nome ";
        pst = conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaArea.add(new Area(
                                rs.getInt("idarea"),
                                rs.getString("nome")));
        }
        
        pst.close();
        return listaArea;
    }
}
