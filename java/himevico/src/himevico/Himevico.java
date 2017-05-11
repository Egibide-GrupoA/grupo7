/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import login.VLogin;

/**
 * Clase Himevico
 *
 * @author Sheila y Asier
 */
public class Himevico {

    static ResultSet rs;

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        GestorBBDD db = new GestorBBDD();
        System.out.print("Contraseña BBDD: ");
        // La contraseña de la BBDD se pedira al iniciar el programa para no publicar la misma en GitHub
        db.connect("program7", br.readLine(), "program7");

        VLogin login = new VLogin();
        login.setVisible(true);
    }
}
