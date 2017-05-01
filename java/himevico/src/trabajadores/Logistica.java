/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajadores;

import centros.Centro;
import himevico.GestorBBDD;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Sheila
 */
public class Logistica extends Trabajador {

    public Logistica() {
    }
    public Logistica(int idTrabajador) throws SQLException {
        this.setIdTrabajador(idTrabajador);
        GestorBBDD.getTrabajadorLogistica(this);
    }

    public Logistica(int idTrabajador, String dni, String nombre, String apellido1, String apellido2, String calle, int portal, int piso, char mano, String telPersonal, String telEmpresa, double salario, Date fechaNacimiento, String contrasena, Centro centro) {
        this.setIdTrabajador(idTrabajador);
        this.setDni(dni);
        this.setNombre(nombre);
        this.setApellido1(apellido1);
        this.setApellido2(apellido2);
        this.setCalle(calle);
        this.setPortal(portal);
        this.setPiso(piso);
        this.setMano(mano);
        this.setTelPersonal(telPersonal);
        this.setTelEmpresa(telEmpresa);
        this.setSalario(salario);
        this.setFechaNacimiento(fechaNacimiento);
        this.setContrasena(contrasena);
        this.setCentro(centro);
        

        //TODO 
        // Conexión BBDD
        // ArrayList/LinkedList
    }
    @Override
    public void eliminar() {
        GestorBBDD.eliminar(this);
        
    }
 
    
}