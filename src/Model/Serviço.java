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
public class Serviço {
    
    private int Id;
    private String Nome;
    private String Tempo;

    public Serviço(int Id, String Nome, String Tempo) {
        this.Id = Id;
        this.Nome = Nome;
        this.Tempo = Tempo;
    }

    public Serviço() {
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

    public String getTempo() {
        return Tempo;
    }

    public void setTempo(String Tempo) {
        this.Tempo = Tempo;
    }
    
    
}
