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
public class Profissional {
    
    private int Id;
    private String Nome;    
    private String Rg; 
    private String Cpf;
    private String Nascimento;
    private String Telefone1;
    private String Telefone2;
    private String Email;
    private String Rua;
    private String Cidade;
    private String Cep;
    private String Bairro;
    private String Numero;
    private String Formacao;
    private int IdArea;

    public Profissional(int Id, String Nome, String Rg, String Cpf, String Nascimento, String Telefone1, String Telefone2, String Email, String Rua, String Cidade, String Cep, String Bairro, String Numero, String Formacao, int IdArea) {
        this.Id = Id;
        this.Nome = Nome;
        this.Rg = Rg;
        this.Cpf = Cpf;
        this.Nascimento = Nascimento;
        this.Telefone1 = Telefone1;
        this.Telefone2 = Telefone2;
        this.Email = Email;
        this.Rua = Rua;
        this.Cidade = Cidade;
        this.Cep = Cep;
        this.Bairro = Bairro;
        this.Numero = Numero;
        this.Formacao = Formacao;
        this.IdArea = IdArea;
    }

    public Profissional() {
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

    public String getRua() {
        return Rua;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFormacao() {
        return Formacao;
    }

    public void setFormacao(String Formacao) {
        this.Formacao = Formacao;
    }

    public int getIdArea() {
        return IdArea;
    }

    public void setIdArea(int IdArea) {
        this.IdArea = IdArea;
    }

    @Override
    public String toString() {
        return getNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
