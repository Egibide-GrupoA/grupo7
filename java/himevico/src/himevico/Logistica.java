/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

/**
 *
 * @author Sheila
 */
public class Logistica extends Trabajador {


   /* public Trabajador(int idTrabajador, GestorBBDD db) throw Exception{
        
        ResultSet rs;
        rs = db.selectPersona(idTrabajador);
        if (rs.next()) {
            this.idTrabajador = rs.getInt("idTrabajador");
            this.dni = rs.getString("dni");
            this.nombre = rs.getString("nombre");
            this.apellido1 = rs.getString("apellido1");
            this.apellido2 = rs.getString("apellido2");
            this.calle = rs.getString("calle");
            this.portal = rs.getInt("portal");
            this.piso = rs.getInt("piso");
            this.mano = rs.getString(mano).charAt(0);
            this.telPersonal = rs.getString("telPersonal");
            this.telEmpresa = rs.getString("telEmpresa");
            this.salario = rs.getDouble("salario");
            this.fechaNacimiento = rs.getDate("fechaNacimiento");
            this.contrasena = rs.getString("contrasena");
            
            
        }
    
    }
    
    */
    
       public Trabajador(int idCentro, String nombre, String calle, int numero, int codPostal, String ciudad, String provincia, String telefono) {
        this.idTrabajador = idTrabajador;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.calle = calle;
        this.portal = portal;
        this.piso = piso;
        this.mano = mano;
        this.telPersonal = telPersonal;
        this.telEmpresa = telEmpresa;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasena = contrasena;
        
    }

    
     
    
}
