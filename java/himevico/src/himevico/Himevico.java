/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author asier
 */
public class Himevico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        // TODO code application logic here
        GestorBBDD db = new GestorBBDD();
        System.out.print("Contraseña BBDD: ");
        // La contraseña de la BBDD se pedira al iniciar el programa para no 
        // publicar la misma en GitHub
        db.connect("program7", br.readLine(), "program7");
    }   
}
