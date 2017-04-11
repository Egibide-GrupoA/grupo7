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
import java.util.Date;
import java.util.List;
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
    public static List<Trabajador> listarTrabajadres() {
     List<Trabajador> trabajadores;
     sql= "SELECT * FROM trabajadores;";
         System.out.println(sql);
     try{  
        rs=stmt.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }

     //trabajadores.add(new Logistica());
     //return trabajadores;
     return null;
    }
    public static ResultSet comprobarUsuario(String usuario,String password) {
     
     sql= "SELECT * FROM `trabajador` WHERE `dni` = '"+usuario+"' AND `contrasena` = '"+password+"';";
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
    
    /*public static ResultSet selectTrabajador(int idTrabajador) {
     
     sql= "SELECT `id` ,`nombre`, `calle`, `numero`, `codPostal`, `ciudad`, `provincia`, `telefono` FROM `centro` WHERE `id` = "+idCentro+";";
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
*/
    public static void getCentro(Centro centro) throws SQLException {
     
     sql= "SELECT `id` ,`nombre`, `calle`, `numero`, `codPostal`, `ciudad`, `piso`, `mano`,  `provincia`, `telefono` FROM `centro` WHERE `id` = "+centro.getIdCentro()+";";
         System.out.println(sql);
     try{  
        rs=stmt.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
    centro.setIdCentro(rs.getInt("id"));
    centro.setNombre(rs.getString("nombre"));
    centro.setCalle(rs.getString("calle"));
    centro.setNumero(rs.getInt("numero"));
    centro.setPiso(rs.getInt("piso"));
    centro.setMano(rs.getString("mano").charAt(0));
    centro.setCodPostal(rs.getInt("codPostal"));
    centro.setCiudad(rs.getString("ciudad"));
    centro.setProvincia(rs.getString("provincia"));
    centro.setTelefono(rs.getString("telefono"));
    }
    public static boolean crearTrabajador(Trabajador trabajador) {
     //TODO: manejo de fechas
     //String dni, String nombre, String apellido1, String apellido2, String calle, int portal, int piso, char mano, String telPersonal, String telEmpresa, double salario, Date fechaNacimiento, int idCategoria, int idCentro, String contrasena
     Centro centro = trabajador.getCentro();
     sql= "INSERT INTO `trabajador` (`id`, `dni`, `nombre`, `apellido1`, `apellido2`, `calle`, `portal`, `piso`, `mano`, `telPersonal`, `telEmpresa`, `salario`, `fechaNacimiento`, `idCategoria`, `idCentro`, `contrasena`) VALUES ("
             + "NULL, '"+trabajador.getDni()+"', '"+trabajador.getNombre()+"', '"+trabajador.getApellido1()+"', '"+trabajador.getApellido2()+"', '"+trabajador.getCalle()+"', '"+trabajador.getPortal()+"', '"+trabajador.getPiso()+"', '"+trabajador.getPortal()+"', NULL, '"+trabajador.getTelPersonal()+"', '"+trabajador.getTelEmpresa()+"', "+trabajador.getSalario()+", '1', '"+centro.getIdCentro()+"', MD5('"+trabajador.getContrasena()+"'));";
     
         System.out.println(sql);
     try{  
        stmt.executeUpdate(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
     return true;
    }
    
    
    public static void crearCentro(Centro centro) {
     
     sql= "INSERT INTO `centro` (`id`, `nombre`, `calle`, `numero`, `piso`, `mano`, `codPostal`,`ciudad`, `provincia`, `telefono`) VALUES ("
             + "NULL, '"+centro.getNombre()+"', '"+centro.getCalle()+"', '"+centro.getNumero()+"', '"+centro.getPiso()+"', '"+centro.getMano()+"', '"+centro.getCodPostal()+"', '"+centro.getCiudad()+"', '"+centro.getProvincia()+"', '"+centro.getTelefono()+"');";
         System.out.println(sql);
     try{  
        stmt.executeUpdate(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     }
    }
}
