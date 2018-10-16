/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Funcionario;
import Model.Profissional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dc
 */
public class ProfissionalDAO {
    
    public void Salvar(Profissional profissional) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "INSERT INTO profissionais set nome = ?, rg = ?, cpf = ?, nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), telefone1 = ?, telefone2 = ?, email = ?, endereco = ?, cidade = ?, cep = ?, bairro = ?,"
                + "numero = ?, formacao = ?, idarea = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, profissional.getNome());
        pst.setString(2, profissional.getRg());
        pst.setString(3, profissional.getCpf());
        pst.setString(4, profissional.getNascimento());
        pst.setString(5, profissional.getTelefone1());
        pst.setString(6, profissional.getTelefone2());
        pst.setString(7, profissional.getEmail());
        pst.setString(8, profissional.getRua());
        pst.setString(9, profissional.getCidade());
        pst.setString(10, profissional.getCep());
        pst.setString(11, profissional.getBairro());
        pst.setString(12, profissional.getNumero());
        pst.setString(13, profissional.getFormacao());
        pst.setInt(14, profissional.getIdArea());
        
        pst.execute();
        pst.close();    
    }
    
    public void Alterar(Profissional profissional) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "UPDATE profissionais set nome = ?, rg = ?, cpf = ?, nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), telefone1 = ?, telefone2 = ?, email = ?, "
                + " endereco = ?, cidade = ?, cep = ?, bairro = ?, numero = ?, formacao = ?, idarea = ? where idprofissionais = ? ";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, profissional.getNome());
        pst.setString(2, profissional.getRg());
        pst.setString(3, profissional.getCpf());
        pst.setString(4, profissional.getNascimento());
        pst.setString(5, profissional.getTelefone1());
        pst.setString(6, profissional.getTelefone2());
        pst.setString(7, profissional.getEmail());
        pst.setString(8, profissional.getRua());
        pst.setString(9, profissional.getCidade());
        pst.setString(10, profissional.getCep());
        pst.setString(11, profissional.getBairro());
        pst.setString(12, profissional.getNumero());
        pst.setString(13, profissional.getFormacao());
        pst.setInt(14, profissional.getIdArea());
        pst.setInt(15, profissional.getId());
        
        pst.execute();
        pst.close(); 
        
    }
    
    public void Excluir(Profissional profissional) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "DELETE from profissionais where idprofissionais = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, profissional.getId());
        
        pst.execute();
        pst.close();;
    }
    
    public List<Profissional> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Profissional> listaprofissionais = new ArrayList<>();

        sql = "select idprofissionais, nome, rg, cpf, DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) AS nascimento, telefone1, telefone2, email, "
            + " endereco, cidade, cep, bairro, numero, formacao, idarea  from profissionais order by nome ";
        pst = conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaprofissionais.add(new Profissional(
                            rs.getInt("idprofissionais"),
                            rs.getString("nome"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("nascimento"),
                            rs.getString("telefone1"),
                            rs.getString("telefone2"),
                            rs.getString("email"),
                            rs.getString("endereco"),
                            rs.getString("cidade"),
                            rs.getString("cep"),
                            rs.getString("bairro"),
                            rs.getString("numero"),
                            rs.getString("formacao"),
                            rs.getInt("idarea")));
        }
    
        pst.close();
        return listaprofissionais;
    }
    
    public List<Profissional> BuscarNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Profissional> listaprofissionais = new ArrayList<>();
        String name = "%"+nome+"%";
        sql = "select idprofissionais, nome, rg, cpf, DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) AS nascimento, telefone1, telefone2, email, "
            + "  endereco, cidade, cep, bairro, numero, formacao, idarea  from profissionais where nome LIKE ? ";

        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaprofissionais.add(new Profissional(
                            rs.getInt("idprofissionais"),
                            rs.getString("nome"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("nascimento"),
                            rs.getString("telefone1"),
                            rs.getString("telefone2"),
                            rs.getString("email"),
                            rs.getString("endereco"),
                            rs.getString("cidade"),
                            rs.getString("cep"),
                            rs.getString("bairro"),
                            rs.getString("numero"),
                            rs.getString("formacao"),
                            rs.getInt("idarea")));
        }
    
        pst.close();
        return listaprofissionais;
    }
    
    public Profissional Buscar(Integer id) throws SQLException{
        PreparedStatement pst;
        String sql;   
        Profissional profissional = null;
        
        sql = "select idprofissionais, nome, rg, cpf, DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) AS nascimento, telefone1, telefone2, email, "
            + " endereco, cidade, cep, bairro, numero, formacao, idarea  from profissionais where idprofissionais = ? ";

        pst = conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            profissional = new Profissional(
                            rs.getInt("idprofissionais"),
                            rs.getString("nome"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("nascimento"),
                            rs.getString("telefone1"),
                            rs.getString("telefone2"),
                            rs.getString("email"),
                            rs.getString("endereco"),
                            rs.getString("cidade"),
                            rs.getString("cep"),
                            rs.getString("bairro"),
                            rs.getString("numero"),
                            rs.getString("formacao"),
                            rs.getInt("idarea"));                    
        }
        
        pst.close();
        return profissional;
    }

}

