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
                + " dataAtendimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ) , horarioAtendimento = STR_TO_DATE( ?, \'%H:%i' )  ";
        pst = conexao.getInstance().prepareStatement(sql);
        
        pst.setInt(1, num);
        pst.setInt(2, agendamento.getIdcliente());
        pst.setInt(3, agendamento.getIdfuncionario());
        pst.setInt(4, agendamento.getIdserviço());
        pst.setString(5, agendamento.getDataAgendamento());
        pst.setString(6, agendamento.getHorarioAgendamento());
        pst.setString(7, agendamento.getDataAtendimento());
        pst.setString(8, agendamento.getHorarioAtendimento());
        pst.execute();
        pst.close();
        
    }
    
    public List<Agendamento> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Agendamento> listaagendamento = new ArrayList<>();
        
        sql = "SELECT idagendamento,idcliente, idfuncionario, idprofissionais, idserviços, DATE_FORMAT( dataAgendamento, \"%d/%m/%Y\" ) AS dataAgendamento, "
                + "TIME_FORMAT(horarioAgendamento, '%H:%i') AS horarioAgendamento, DATE_FORMAT( dataCancelamento, \"%d/%m/%Y\" ) AS dataCancelamento, "
                + "DATE_FORMAT( dataAtendimento, \"%d/%m/%Y\" ) AS dataAtendimento, TIME_FORMAT(horarioAtendimento, '%H:%i') AS horarioAtendimento FROM agendamento";
        pst = conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaagendamento.add(new Agendamento(
                            rs.getInt("idagendamento"),
                            rs.getInt("idcliente"),
                            rs.getInt("idprofissionais"),
                            rs.getInt("idserviços"),
                            rs.getInt("idserviços"),
                            rs.getString("dataAgendamento"),
                            rs.getString("horarioAgendamento"),
                            rs.getString("dataCancelamento"),
                            rs.getString("dataAtendimento"),
                            rs.getString("horarioAtendimento")
                        ));
        }
        return listaagendamento;
    }
}
