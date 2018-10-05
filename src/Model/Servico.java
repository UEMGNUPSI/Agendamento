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
public class Servico {
    
    private int Id;
    private String Descricao;
    private String Tempo;
    private String Area;

    public Servico(int Id, String Descricao, String Area, String Tempo) {
        this.Id = Id;
        this.Descricao = Descricao;
        this.Area = Area;
        this.Tempo = Tempo;
       
    }

    public Servico() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getTempo() {
        return Tempo;
    }

    public void setTempo(String Tempo) {
        this.Tempo = Tempo;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    
    
}
