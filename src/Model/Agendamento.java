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
public class Agendamento {
    
    private int Id;
    private Cliente cliente;
    private Funcionário funcionario;
    private Profissional profissional;
    private Serviço serviço;
    private String Dia;
    private String Horario;

    public Agendamento(int Id, Cliente cliente, Funcionário funcionario, Profissional profissional, Serviço serviço, String Dia, String Horario) {
        this.Id = Id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.profissional = profissional;
        this.serviço = serviço;
        this.Dia = Dia;
        this.Horario = Horario;
    }

    public Agendamento() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionário getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionário funcionario) {
        this.funcionario = funcionario;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Serviço getServiço() {
        return serviço;
    }

    public void setServiço(Serviço serviço) {
        this.serviço = serviço;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String Dia) {
        this.Dia = Dia;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
    }

}
