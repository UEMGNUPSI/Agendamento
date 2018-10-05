/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Valida;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Dc
 */
public class ValidaData {
   
    public static boolean ValidarData(String data){
    DateFormat valida = new SimpleDateFormat("dd/MM/yyyy");
            valida.setLenient (false);
            try {
                valida.parse (data);
                return true;
            } catch (ParseException ex) {
               return false;
            }

    }
    
}
