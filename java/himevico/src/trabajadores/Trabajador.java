/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajadores;

import centros.Centro;
import himevico.GestorBBDD;
import java.util.ArrayList;
import java.util.Date;
import partes.Parte;

/**
 * Clase Trabajador
 *
 * @author Sheila y Asier
 */
public abstract class Trabajador {

    /**
     * Eliminar en BBDD este trbajador
     */
    public void eliminar() {
        GestorBBDD.eliminar(this);

    }

    private int idTrabajador;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String calle;
    private int portal;
    private int piso;
    private char mano;
    private String telPersonal;
    private String telEmpresa;
    private double salario;
    private Date fechaNacimiento;
    private String contrasena;
    private Centro centro;

    private ArrayList<Parte> partes = new ArrayList<>();

    /**
     *
     * @return
     */
    public Centro getCentro() {
        return centro;
    }

    /**
     *
     * @param centro
     */
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    /**
     * @return the idTrabajador
     */
    public int getIdTrabajador() {
        return idTrabajador;
    }

    /**
     * @param idTrabajador the idTrabajador to set
     */
    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the portal
     */
    public int getPortal() {
        return portal;
    }

    /**
     * @param portal the portal to set
     */
    public void setPortal(int portal) {
        this.portal = portal;
    }

    /**
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }

    /**
     * @return the mano
     */
    public char getMano() {
        return mano;
    }

    /**
     * @param mano the mano to set
     */
    public void setMano(char mano) {
        this.mano = mano;
    }

    /**
     * @return the telPersonal
     */
    public String getTelPersonal() {
        return telPersonal;
    }

    /**
     * @param telPersonal the telPersonal to set
     */
    public void setTelPersonal(String telPersonal) {
        this.telPersonal = telPersonal;
    }

    /**
     * @return the telEmpresa
     */
    public String getTelEmpresa() {
        return telEmpresa;
    }

    /**
     * @param telEmpresa the telEmpresa to set
     */
    public void setTelEmpresa(String telEmpresa) {
        this.telEmpresa = telEmpresa;
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Actualizar en BBDD este trabajador
     */
    public void actualizar() {
        GestorBBDD.actualizarTrabajador(this);
    }

}
