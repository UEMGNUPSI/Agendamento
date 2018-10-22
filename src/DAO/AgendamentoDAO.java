/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Agendamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Dc
 */
public class AgendamentoDAO {
    
    public void Salvar(Agendamento agendamento) throws SQLException{
        PreparedStatement pst;
        String sql;
        int num = 1;
        sql = "INSERT INTO agendamento SET idfuncionario = ?, idcliente = ?, idprofissionais = ?, idserviços = ?,"
                + " dataAgendamento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), horarioAgendamento = STR_TO_DATE( ?, \'%H:%i' ) ,  "
                + " dataAtendimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ) , horarioAtendimento = STR_TO_DATE( ?, \'%H:%i' ),  "
                + "horarioPrevistoTermino = STR_TO_DATE(?,\'%H:%i' )";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setInt(1, num);
        pst.setInt(2, agendamento.getIdcliente());
        pst.setInt(3, agendamento.getIdprofissional());
        pst.setInt(4, agendamento.getIdserviço());
        pst.setString(5, agendamento.getDataAgendamento());
        pst.setString(6, agendamento.getHorarioAgendamento());
        pst.setString(7, agendamento.getDataAtendimento());
        pst.setString(8, agendamento.getHorarioAtendimento());
        pst.setString(9, agendamento.getHorarioPrevistoTermino());
        pst.execute();
        pst.close();
        
    }
    
    public void Remarcar(Agendamento agendamento) throws SQLException{
        PreparedStatement pst;
        String sql;
        int id = 1;
        sql = "UPDATE agendamento SET idcliente = ?, idfuncionario = ?, idprofissionais = ?, idserviços = ?, dataAgendamento = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + " horarioAgendamento = STR_TO_DATE( ?, \'%H:%i' ), horarioPrevistoTermino = STR_TO_DATE(?,\'%H:%i' ) WHERE idagendamento = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setInt(1, agendamento.getIdcliente());
        pst.setInt(2, id);
        pst.setInt(3, agendamento.getIdprofissional());
        pst.setInt(4, agendamento.getIdserviço());
        pst.setString(5, agendamento.getDataAgendamento());
        pst.setString(6, agendamento.getHorarioAgendamento());
        pst.setString(7, agendamento.getHorarioPrevistoTermino());
        pst.setInt(8, agendamento.getId());
        pst.execute();
        pst.close();
 
    }
    
    public void Cancelamento(Integer id) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "DELETE FROM agendamento WHERE idagendamento = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setInt(1,id);
        pst.execute();
        pst.close();
    }
    
    public Agendamento Buscar(Integer id) throws SQLException{
        PreparedStatement pst;
        String sql;
        Agendamento agendamento = null;
        
        sql = "SELECT idagendamento, idfuncionario, idcliente, idprofissionais, idserviços, TIME_FORMAT(horarioAgendamento, '%H:%i') AS horarioAgendamento,"
                + " DATE_FORMAT( dataAgendamento, \"%d/%m/%Y\" ) AS dataAgendamento FROM agendamento WHERE idagendamento = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            agendamento = new Agendamento(
                            rs.getInt("idagendamento"),
                            rs.getInt("idcliente"),
                            rs.getInt("idfuncionario"),
                            rs.getInt("idprofissionais"),
                            rs.getInt("idserviços"),
                            rs.getString("dataAgendamento"),
                            rs.getString("horarioAgendamento"));
                            
        }
        pst.close();
        return agendamento;
    
    }
    
    public List<Agendamento> listaTodos(String data) throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Agendamento> listaagendamento = new ArrayList<>();
        
        sql = "SELECT idagendamento,idcliente, idfuncionario, idprofissionais, idserviços, DATE_FORMAT( dataAgendamento, \"%d/%m/%Y\" ) AS dataAgendamento, "
                + "TIME_FORMAT(horarioAgendamento, '%H:%i') AS horarioAgendamento, TIME_FORMAT(horarioPrevistoTermino, '%H:%i') AS horarioPrevistoTermino, "
                + "DATE_FORMAT( dataCancelamento, \"%d/%m/%Y\" ) AS dataCancelamento, DATE_FORMAT( dataAtendimento, \"%d/%m/%Y\" ) AS dataAtendimento, "
                + "TIME_FORMAT(horarioAtendimento, '%H:%i') AS horarioAtendimento FROM agendamento WHERE dataAgendamento = STR_TO_DATE( ?, \"%d/%m/%Y\" ) order by horarioAgendamento  ";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, data);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaagendamento.add(new Agendamento(
                            rs.getInt("idagendamento"),
                            rs.getInt("idcliente"),
                            rs.getInt("idfuncionario"),
                            rs.getInt("idprofissionais"),
                            rs.getInt("idserviços"),
                            rs.getString("dataAgendamento"),
                            rs.getString("horarioAgendamento"),
                            rs.getString("horarioPrevistoTermino"),
                            rs.getString("dataCancelamento"),
                            rs.getString("dataAtendimento"),
                            rs.getString("horarioAtendimento")
                        ));
        }
        return listaagendamento;
    }
    
    public List<Agendamento> verificarVaga(String Id, String inicio,String fim,String data) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<Agendamento> listaagendamento = new ArrayList<>();
        
        sql = "Select idagendamento,idcliente, idfuncionario, idprofissionais, idserviços, DATE_FORMAT( dataAgendamento, \"%d/%m/%Y\" ) AS dataAgendamento, "
                + "TIME_FORMAT(horarioAgendamento, '%H:%i') AS horarioAgendamento, TIME_FORMAT(horarioPrevistoTermino, '%H:%i') AS horarioPrevistoTermino, "
                + "DATE_FORMAT( dataCancelamento, \"%d/%m/%Y\" ) AS dataCancelamento, DATE_FORMAT( dataAtendimento, \"%d/%m/%Y\" ) AS dataAtendimento, "
                + "TIME_FORMAT(horarioAtendimento, '%H:%i') AS horarioAtendimento "
                + "FROM pratileira.agendamento where  idprofissionais = ? AND ( dataAgendamento = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AND ( horarioAgendamento between ? AND ? "
                + "OR horarioPrevistoTermino between ? AND ? ))  order by horarioAgendamento ";
        
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, Integer.parseInt(Id));
        pst.setString(2, data);
        pst.setString(3, inicio);
        pst.setString(4, fim);
        pst.setString(5, inicio);
        pst.setString(6, fim);
        
        
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaagendamento.add(new Agendamento(
                            rs.getInt("idagendamento"),
                            rs.getInt("idcliente"),
                            rs.getInt("idfuncionario"),
                            rs.getInt("idprofissionais"),
                            rs.getInt("idserviços"),
                            rs.getString("dataAgendamento"),
                            rs.getString("horarioAgendamento"),
                            rs.getString("horarioPrevistoTermino"),
                            rs.getString("dataCancelamento"),
                            rs.getString("dataAtendimento"),
                            rs.getString("horarioAtendimento")
                        ));
        }
        return listaagendamento;
    }
    
   public List<Agendamento> BuscarNome(String filtro, String nome, String data) throws SQLException{
        PreparedStatement pst;
        String sql; 
        String agendamento = "agendamento.id"+filtro;
        String segundaTabela = filtro+".id"+filtro;
        String name = "%"+nome+"%";
        List<Agendamento> listaagendamento = new ArrayList<>();

        sql = "SELECT idagendamento, agendamento.idcliente, agendamento.idfuncionario, agendamento.idprofissionais, agendamento.idserviços, DATE_FORMAT( dataAgendamento, \"%d/%m/%Y\" ) AS dataAgendamento, "
                + "TIME_FORMAT(horarioAgendamento, '%H:%i') AS horarioAgendamento, TIME_FORMAT(horarioPrevistoTermino, '%H:%i') AS horarioPrevistoTermino, "
                + "DATE_FORMAT( dataCancelamento, \"%d/%m/%Y\" ) AS dataCancelamento, DATE_FORMAT( dataAtendimento, \"%d/%m/%Y\" ) AS dataAtendimento, "
                + "TIME_FORMAT(horarioAtendimento, '%H:%i') AS horarioAtendimento FROM agendamento INNER JOIN "+filtro+" ON "+agendamento+" = "+segundaTabela+" WHERE nome LIKE ? AND dataAgendamento = STR_TO_DATE( ?, \"%d/%m/%Y\" ) ORDER BY horarioAgendamento";
        
        //sql = "SELECT idagendamento,agendamento.idcliente, idfuncionario, idprofissionais, idserviços, dataAgendamento,horarioAgendamento,horarioPrevistoTermino, dataCancelamento, dataAtendimento,horarioAtendimento FROM agendamento INNER JOIN "+filtro+" ON agendamento.idcliente = cliente.idcliente WHERE nome LIKE ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, data);
        
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaagendamento.add(new Agendamento(
                            rs.getInt("idagendamento"),
                            rs.getInt("idcliente"),
                            rs.getInt("idfuncionario"),
                            rs.getInt("idprofissionais"),
                            rs.getInt("idserviços"),
                            rs.getString("dataAgendamento"),
                            rs.getString("horarioAgendamento"),
                            rs.getString("horarioPrevistoTermino"),
                            rs.getString("dataCancelamento"),
                            rs.getString("dataAtendimento"),
                            rs.getString("horarioAtendimento")
                        ));
        }
        return listaagendamento;
    } 
}
