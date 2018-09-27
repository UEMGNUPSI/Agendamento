/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Dc
 */
public class Cliente {
    
    private int Id;
    private String Nome;
    private String Rg; 
    private String Cpf;
    private String Nascimento;
    private String Endereço; 
    private String Telefone1;
    private String Telefone2;
    private String Email;
    private String Descrição;

    public Cliente(int Id, String Nome, String Rg, String Cpf, String Nascimento, String Endereço, String Telefone1, String Telefone2, String Email, String Descrição) {
        this.Id = Id;
        this.Nome = Nome;
        this.Rg = Rg;
        this.Cpf = Cpf;
        this.Nascimento = Nascimento;
        this.Endereço = Endereço;
        this.Telefone1 = Telefone1;
        this.Telefone2 = Telefone2;
        this.Email = Email;
        this.Descrição = Descrição;
    }

    public Cliente() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getRg() {
        return Rg;
    }

    public void setRg(String Rg) {
        this.Rg = Rg;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getNascimento() {
        return Nascimento;
    }

    public void setNascimento(String Nascimento) {
        this.Nascimento = Nascimento;
    }

    public String getEndereço() {
        return Endereço;
    }

    public void setEndereço(String Endereço) {
        this.Endereço = Endereço;
    }

    public String getTelefone1() {
        return Telefone1;
    }

    public void setTelefone1(String Telefone1) {
        this.Telefone1 = Telefone1;
    }

    public String getTelefone2() {
        return Telefone2;
    }

    public void setTelefone2(String Telefone2) {
        this.Telefone2 = Telefone2;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDescrição() {
        return Descrição;
    }

    public void setDescrição(String Descrição) {
        this.Descrição = Descrição;
    }
    
    
    
}
