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
    private int Idcliente;
    private int Idfuncionario;
    private int Idprofissional;
    private int Idserviço;
    private String dataAgendamento;
    private String horarioAgendamento;
    private String dataCancelamento;
    private String dataAtendimento;
    private String horarioAtendimento;

    public Agendamento(int Id, int Idcliente, int Idfuncionario, int Idprofissional, int Idserviço, String dataAgendamento, String horarioAgendamento, String dataCancelamento, String dataAtendimento, String horarioAtendimento) {
        this.Id = Id;
        this.Idcliente = Idcliente;
        this.Idfuncionario = Idfuncionario;
        this.Idprofissional = Idprofissional;
        this.Idserviço = Idserviço;
        this.dataAgendamento = dataAgendamento;
        this.horarioAgendamento = horarioAgendamento;
        this.dataCancelamento = dataCancelamento;
        this.dataAtendimento = dataAtendimento;
        this.horarioAtendimento = horarioAtendimento;
    }

    public Agendamento() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdcliente() {
        return Idcliente;
    }

    public void setIdcliente(int Idcliente) {
        this.Idcliente = Idcliente;
    }

    public int getIdfuncionario() {
        return Idfuncionario;
    }

    public void setIdfuncionario(int Idfuncionario) {
        this.Idfuncionario = Idfuncionario;
    }

    public int getIdprofissional() {
        return Idprofissional;
    }

    public void setIdprofissional(int Idprofissional) {
        this.Idprofissional = Idprofissional;
    }

    public int getIdserviço() {
        return Idserviço;
    }

    public void setIdserviço(int Idserviço) {
        this.Idserviço = Idserviço;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getHorarioAgendamento() {
        return horarioAgendamento;
    }

    public void setHorarioAgendamento(String horarioAgendamento) {
        this.horarioAgendamento = horarioAgendamento;
    }

    public String getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(String dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public void setHorarioAtendimento(String horarioAtendimento) {
        this.horarioAtendimento = horarioAtendimento;
    }

   
   

    

   
}
