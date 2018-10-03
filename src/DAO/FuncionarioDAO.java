/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class FuncionarioDAO {
    
    public void Salvar(Funcionario funcionario) throws SQLException {
        PreparedStatement pst;
        String sql;
        
        sql = "INSERT INTO funcionario set nome = ?, rg = ?, cpf = ?, nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), telefone1 = ?, telefone2 = ?, email = ?, "
                + " endereco = ?, numero = ?, bairro = ?, cidade = ?, cep = ?, login = ?, senha = ?, nivel = ? ";
        
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, funcionario.getNome());
        pst.setString(2, funcionario.getRg());
        pst.setString(3, funcionario.getCpf());
        pst.setString(4, funcionario.getNascimento());
        pst.setString(5, funcionario.getTelefone1());
        pst.setString(6, funcionario.getTelefone2());
        pst.setString(7, funcionario.getEmail());
        pst.setString(8, funcionario.getRua());
        pst.setString(9, funcionario.getNumero());
        pst.setString(10, funcionario.getBairro());
        pst.setString(11, funcionario.getCidade());
        pst.setString(12, funcionario.getCep());
        pst.setString(13, funcionario.getLogin());       
        pst.setString(14, funcionario.getSenha());
        pst.setBoolean(15, funcionario.getNivel());
        
        pst.execute();
        pst.close();
    }
    
    public void Alterar(Funcionario funcionario) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "update funcionario set nome = ?, rg = ?, cpf = ?, nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), telefone1 = ?, telefone2 = ?, email = ?, "
                + " endereco = ?, numero = ?, bairro = ?, cidade = ?, cep = ?, login = ?, senha = ?, nivel = ? where idfuncionario = ? ";
        
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, funcionario.getNome());
        pst.setString(2, funcionario.getRg());
        pst.setString(3, funcionario.getCpf());
        pst.setString(4, funcionario.getNascimento());
        pst.setString(5, funcionario.getTelefone1());
        pst.setString(6, funcionario.getTelefone2());
        pst.setString(7, funcionario.getEmail());
        pst.setString(8, funcionario.getRua());
        pst.setString(9, funcionario.getNumero());
        pst.setString(10, funcionario.getBairro());
        pst.setString(11, funcionario.getCidade());
        pst.setString(12, funcionario.getCep());
        pst.setString(13, funcionario.getLogin());       
        pst.setString(14, funcionario.getSenha());
        pst.setBoolean(15, funcionario.getNivel());
        pst.setInt(16, funcionario.getId());
        
        pst.execute();
        pst.close();
    }
    
    public void Excluir(Funcionario funcionario) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "delete from funcionario where idfuncionario = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, funcionario.getId());
        pst.execute();
        pst.close();
    }
    public List<Funcionario> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Funcionario> listafuncionario = new ArrayList<>();

        sql = "select idfuncionario, nome, rg, cpf, DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) AS nascimento, telefone1, telefone2, email, "
            + " endereco, numero, bairro, cidade, cep, login, senha, nivel from funcionario order by nome ";
        pst = conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listafuncionario.add(new Funcionario(
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
                            rs.getBoolean("nivel")));
        }
    
        pst.close();
        return listafuncionario;
    }
    
    public List<Funcionario> BuscarNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Funcionario> listafuncionario = new ArrayList<>();
        String name = "%"+nome+"%";
        sql = "select idfuncionario, nome, rg, cpf, DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) AS nascimento, telefone1, telefone2, email, "
            + " endereco, numero, bairro, cidade, cep, login, senha, nivel from funcionario where nome LIKE ? ";

        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listafuncionario.add(new Funcionario(
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
                            rs.getBoolean("nivel")));
        }
    
        pst.close();
        return listafuncionario;
    }
    
    public Funcionario Buscar(Integer id) throws SQLException{
        PreparedStatement pst;
        String sql;   
        Funcionario funcionario = null;
        
        sql = "select idfuncionario, nome, rg, cpf, DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) AS nascimento, telefone1, telefone2, email, "
            + " endereco, numero, bairro, cidade, cep, login, senha, nivel from funcionario where idfuncionario = ?";

        pst = conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
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
                            rs.getString("endereco"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("cep"),
                            rs.getString("email"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getBoolean("nivel"));
                            
        }
        pst.close();
        return funcionario;
    }

}