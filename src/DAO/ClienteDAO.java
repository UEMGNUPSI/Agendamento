/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONTROLLER.conexao;
import Model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dc
 */
public class ClienteDAO {
    
    
    public void Salvar(Cliente cliente) throws SQLException {
        PreparedStatement pst;
        String sql;
        
        sql = "INSERT INTO cliente set nome = ?, rg = ?, cpf = ?, nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), telefone1 = ?, telefone2 = ?, email = ?, "
                + "descricao = ?, endereco = ?, numero = ?, bairro = ?, cidade = ?, cep = ?, ativo = 0";
        
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, cliente.getNome());
        pst.setString(2, cliente.getRg());
        pst.setString(3, cliente.getCpf());
        pst.setString(4, cliente.getNascimento());
        pst.setString(5, cliente.getTelefone1());
        pst.setString(6, cliente.getTelefone2());
        pst.setString(7, cliente.getEmail());
        pst.setString(8, cliente.getDescrição());
        pst.setString(9, cliente.getRua());
        pst.setString(10, cliente.getNumero());
        pst.setString(11, cliente.getBairro());
        pst.setString(12, cliente.getCidade());
        pst.setString(13, cliente.getCep());
        
        pst.execute();
        pst.close();
    }
    
    public void Alterar(Cliente cliente) throws SQLException{
        PreparedStatement pst;
        String sql;
        
        sql = "update cliente set nome = ?, rg = ?, cpf = ?, nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), telefone1 = ?, telefone2 = ?, email = ?, "
                + "descricao = ?, endereco = ?, numero = ?, bairro = ?, cidade = ?, cep = ? where idcliente = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, cliente.getNome());
        pst.setString(2, cliente.getRg());
        pst.setString(3, cliente.getCpf());
        pst.setString(4, cliente.getNascimento());
        pst.setString(5, cliente.getTelefone1());
        pst.setString(6, cliente.getTelefone2());
        pst.setString(7, cliente.getEmail());
        pst.setString(8, cliente.getDescrição());
        pst.setString(9, cliente.getRua());
        pst.setString(10, cliente.getNumero());
        pst.setString(11, cliente.getBairro());
        pst.setString(12, cliente.getCidade());
        pst.setString(13, cliente.getCep());
        pst.setInt(14, cliente.getId());
        pst.execute();
        pst.close();
        
    }
    
    public void Excluir(Cliente cliente) throws SQLException{
            PreparedStatement pst;
            String sql; 
            
            sql = "update cliente set ativo = 1 where idcliente = ?";
            
            pst = conexao.getInstance().prepareStatement(sql);
            pst.setInt(1, cliente.getId());
            pst.execute();
            pst.close();        
    }
    
    public List<Cliente> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Cliente> listacliente = new ArrayList<>();
        
        sql = "select idcliente, nome, rg, cpf, DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) AS nascimento, telefone1, telefone2, email, "
                + "descricao, endereco, numero, bairro, cidade, cep from cliente where ativo = 0 order by nome ";
        pst = conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listacliente.add(new Cliente(
                            rs.getInt("idcliente"),
                            rs.getString("nome"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("nascimento"),
                            rs.getString("telefone1"),
                            rs.getString("telefone2"),
                            rs.getString("email"),
                            rs.getString("descricao"),
                            rs.getString("endereco"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("cep")));
        }
    
        pst.close();
        return listacliente;
    }
    
    public List<Cliente> BuscarNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;   
        List<Cliente> listacliente = new ArrayList<>();
        String name = "%"+nome+"%";
        
        sql = "select idcliente, nome, rg, cpf, DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) AS nascimento, telefone1, telefone2, email, "
                + "descricao, endereco, numero, bairro, cidade, cep from cliente where nome LIKE ? AND ativo = 0 ";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listacliente.add(new Cliente(
                            rs.getInt("idcliente"),
                            rs.getString("nome"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("nascimento"),
                            rs.getString("telefone1"),
                            rs.getString("telefone2"),
                            rs.getString("email"),
                            rs.getString("descricao"),
                            rs.getString("endereco"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("cep")));
        }
    
        pst.close();
        return listacliente;
    }
    
    public Cliente Buscar(Integer id) throws SQLException{
        PreparedStatement pst;
        String sql;   
        Cliente cliente = null;
        
        sql = "select idcliente, nome, rg, cpf, DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) AS nascimento, telefone1, telefone2, email, "
                + "descricao, endereco, numero, bairro, cidade, cep from cliente where idcliente = ?";
        pst = conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            cliente = new Cliente(
                            rs.getInt("idcliente"),
                            rs.getString("nome"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("nascimento"),
                            rs.getString("telefone1"),
                            rs.getString("telefone2"),
                            rs.getString("email"),
                            rs.getString("descricao"),
                            rs.getString("endereco"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("cep"));
                            
        }
        pst.close();
        return cliente;
    }
    
    
    
    
}
