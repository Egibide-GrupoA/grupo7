/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sheila
 * @author Asier
 */
public class GestorBBDD {
    private static Connection Conexion;
    static String sql;
    static ResultSet rs;
    static Statement stmt = null;
    public void connect(String user, String pass, String db_name) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://ireland.izabil.net:3306/" + db_name, user, pass);
            stmt = Conexion.createStatement();
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static ResultSet selectAll(String table) {
     
     sql= "SELECT * FROM "+table+";";
         System.out.println(sql);
     try{  
        rs=stmt.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
     return rs;
    }
    public static ResultSet comprobarUsuario(String usuario,String password) {
     
     sql= "SELECT * FROM `persona` WHERE `dni` = '"+usuario+"' AND `contrasena` = '"+password+"';";
         System.out.println(sql);
     try{  
        rs=stmt.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
     return rs;
    }
}
