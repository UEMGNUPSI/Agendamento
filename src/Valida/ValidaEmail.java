/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Valida;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dc
 */
public class ValidaEmail {
    
    public static boolean ValidarEmail(String email){
        
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
	Matcher m = p.matcher(email);
	boolean matchFound = m.matches();
        
        if(matchFound){
            return true;
        }
        else{
            return false;
        }
    }
    
}
