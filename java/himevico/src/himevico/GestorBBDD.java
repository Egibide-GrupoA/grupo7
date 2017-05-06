/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

import vehiculo.Vehiculo;
import trabajadores.Trabajador;
import trabajadores.Logistica;
import viajes.Viaje;
import centros.Centro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import login.VLogin;
import partes.Parte;
/**
 *
 * @author Sheila
 * @author Asier
 */
public class GestorBBDD {
    private static Connection Conexion;
    static String sql;
    static ResultSet rs;
    static ResultSet rs2;
    static Statement stmt = null;
    static Statement stmt2 = null;
    public void connect(String user, String pass, String db_name) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://ireland.izabil.net:3306/" + db_name, user, pass);
            stmt = Conexion.createStatement();
            //TODO: ARREGLAR PARCHE RAPIDO
            stmt2 = Conexion.createStatement();
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static List<Logistica> listarTrabajadoresLogistica() throws SQLException, Exception {
     List<Logistica> logiticos = new ArrayList<>();
     sql= "SELECT * FROM trabajador;";
         System.out.println(sql);
     try{  
        rs2=stmt2.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
    while (rs2.next()) {
        
        logiticos.add(new Logistica(rs2.getInt("id")));
       
        System.out.println("TEST1");
    }
     return logiticos;
    }
    
    
    public static List<Centro> listarCentros() throws SQLException, Exception {
     List<Centro> centros = new ArrayList<>();
     sql= "SELECT * FROM centro;";
         System.out.println(sql);
     try{  
        rs2=stmt2.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
    while (rs2.next()) {
        
        centros.add(new Centro(rs2.getInt("id")));
       
        System.out.println("TEST1");
    }
     return centros;
    }
    
    public static List<Vehiculo> listarVehiculos() throws SQLException, Exception {
     List<Vehiculo> vehiculos = new ArrayList<>();
     sql= "SELECT `matricula`, `marca`, `modelo`, `color`, `fechaAlta` FROM vehiculo;";
         System.out.println(sql);
     try{  
        rs2=stmt2.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
    while (rs2.next()) {
        
        vehiculos.add(new Vehiculo(rs2.getString("matricula")));
       
        System.out.println("TEST1");
    }
     return vehiculos;
    }
        
    
    public static void getVehiculo(Vehiculo vehiculo) throws SQLException {
     
        sql= "SELECT `matricula`, `marca`, `modelo`, `color`, `fechaAlta` FROM `vehiculo` WHERE `matricula` = '"+vehiculo.getMatricula()+"';";
        System.out.println(sql);
        rs=executeQuery(sql);
        rs.next();
        vehiculo.setMatricula(rs.getString("matricula"));
        vehiculo.setMarca(rs.getString("marca"));
        vehiculo.setModelo(rs.getString("modelo"));
        vehiculo.setColor(rs.getString("color"));
        vehiculo.setFechaAlta(rs.getDate("fechaAlta"));

        
    }
         
    public static boolean comprobarUsuario(String usuario,String password) {
     
     sql= "SELECT * FROM `trabajador` WHERE `dni` = '"+usuario+"' AND `contrasena` = '"+password+"';";
         System.out.println(sql);
     try{  
        rs=stmt.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
        try {
        if (rs.next()) {
           return true;
        }else{
           return false;
        }
        } catch (SQLException ex) {
        Logger.getLogger(VLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    
    // Recordar contraseña
    public static String tipoUsuario(String dni) throws SQLException{
    
    sql= "SELECT categoria.nombre tipoUsuario FROM `trabajador` INNER JOIN categoria ON trabajador.idCategoria = categoria.id WHERE trabajador.dni = '"+dni+"';";
        System.out.println(sql);
    try{  
        rs=stmt.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
     rs.next();
     return rs.getString("tipoUsuario");
    }
    
    
    
    /*
public static ResultSet selectTrabajador(int idTrabajador) {
     
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
    public static void getTrabajadorLogistica(Logistica logistica) throws SQLException {
     
        sql= "SELECT `id`, `dni`, `nombre`, `apellido1`, `apellido2`, `calle`, `numero`, `piso`, `mano`, `telPersonal`, `telEmpresa`, `salario`, `fechaNacimiento`, `idCategoria`, `idCentro` FROM `trabajador` WHERE `id` = "+logistica.getIdTrabajador()+";";
        rs=executeQuery(sql);
        rs.next();
        //logistica.setIdCentro(rs.getInt("id"));
        logistica.setNombre(rs.getString("nombre"));
        logistica.setCalle(rs.getString("calle"));
        //logistica.setNumero(rs.getInt("numero"));
        logistica.setPiso(rs.getInt("piso"));
        logistica.setMano(rs.getString("mano").charAt(0));
        //logistica.setCodPostal(rs.getInt("codPostal"));
        //logistica.setCiudad(rs.getString("ciudad"));
        //logistica.setProvincia(rs.getString("provincia"));
        //logistica.setTelefono(rs.getString("telefono"));
    }
    public static void getCentro(Centro centro) throws SQLException {
     
     sql= "SELECT `id` ,`nombre`, `calle`, `numero`, `codPostal`, `ciudad`, `piso`, `mano`,  `provincia`, `telefono` FROM `centro` WHERE `id` = "+centro.getIdCentro()+";";
     rs=executeQuery(sql);
    rs.next();
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
        executeUpdate(sql); 
     return true;
    }
    
    
    public static void crearCentro(Centro centro) {
     
     sql= "INSERT INTO `centro` (`id`, `nombre`, `calle`, `numero`, `piso`, `mano`, `codPostal`,`ciudad`, `provincia`, `telefono`) VALUES ("
             + "NULL, '"+centro.getNombre()+"', '"+centro.getCalle()+"', '"+centro.getNumero()+"', '"+centro.getPiso()+"', '"+centro.getMano()+"', '"+centro.getCodPostal()+"', '"+centro.getCiudad()+"', '"+centro.getProvincia()+"', '"+centro.getTelefono()+"');";
     executeUpdate(sql); 
    }
    
    public static void eliminar(Centro centro){
        sql="DELETE FROM `centro` WHERE `centro`.`id` = " + centro.getIdCentro();
        executeUpdate(sql); 
    }
    public static void eliminar(Trabajador trabajador){
        sql="DELETE FROM `trabajador` WHERE `trabajador`.`id` = " + trabajador.getIdTrabajador();
        executeUpdate(sql); 
    }
    private static void executeUpdate(String sql){
             System.out.println(sql);
     try{  
        stmt.executeUpdate(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     }
    }
    private static ResultSet executeQuery(String sql){
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
    
    public static void crearVehiculo(Vehiculo vehiculo) {
     
     sql= "INSERT INTO `vehiculo` (`matricula`, `marca`, `modelo`, `color`, `fechaAlta`) VALUES ('"+vehiculo.getMatricula()+"', '"+vehiculo.getMarca()+"', '"+vehiculo.getModelo()+"', '"+vehiculo.getColor()+"', CURRENT_TIMESTAMP);";
     executeUpdate(sql); 
    }
    
    public static void crearViaje(Viaje viaje) {
     
     sql= "INSERT INTO `viaje` (`id`, `idParte`, `horaInicio`, `horaFin`, `matricula`, `albaran`) VALUES (NULL, '1', '"+viaje.getHoraInicio().getHours()+":"+viaje.getHoraInicio().getMinutes()+":00', '"+viaje.getHoraFin().getHours()+":"+viaje.getHoraFin().getMinutes()+":00', '"+viaje.getMatricula()+"', '"+viaje.getAlbaran()+"' );";
     executeUpdate(sql); 
    }
        
    public static void getViaje(Viaje viaje) throws SQLException {
     
     sql= "SELECT `id` ,`hora inicio`, `hora fin`, `matricula`FROM `centro` WHERE `id` = "+viaje.getIdViaje()+";";
     rs=executeQuery(sql);
     rs.next();
     viaje.setIdViaje(rs.getInt("id"));
     //viaje.setHoraInicio(rs.getInt("hora inicio"));
     //viaje.setHoraFin(rs.getInt("hora fin"));
     //viaje.setMatricula(rs.getString("matricula"));
 
    }
    public static List<Viaje> listarViajes() throws SQLException, Exception {
     List<Viaje> viajes = new ArrayList<>();
     sql= "SELECT * FROM viaje;";
         System.out.println(sql);
     try{  
        rs2=stmt2.executeQuery(sql);
     }
     catch(SQLException e)
     {
         System.out.println(e);
     
     }
    while (rs2.next()) {
        
        viajes.add(new Viaje(rs2.getInt("id")));
       
        System.out.println("TEST1");
    }
     return viajes;
    }
}
