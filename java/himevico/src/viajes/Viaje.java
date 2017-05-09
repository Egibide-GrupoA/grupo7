/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viajes;

import himevico.GestorBBDD;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import partes.Parte;
import vehiculo.Vehiculo;

/**
 * Clase viaje
 * @author Sheila y Asier
 */
public class Viaje {
    
    private int idViaje;
    private Parte parte;
    private Date horaInicio;
    private Date horaFin;
    private vehiculo.Vehiculo vehiculo;
    private String albaran;

    public Viaje() {
    }
    
    /**
     * Constructor que genera un objeto de tipo viaje
     * @param horaInicio
     * @param horaFin
     * @param vehiculo
     * @param albaran
     * @param parte 
     */

    public Viaje(Date horaInicio, Date horaFin, vehiculo.Vehiculo vehiculo, String albaran, Parte parte) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.vehiculo = vehiculo;
        this.albaran = albaran;
        this.parte = parte;


        GestorBBDD.crearViaje(this);
    }

     public Viaje(int idViaje) throws SQLException {
        this.idViaje = idViaje;
        GestorBBDD.getViaje(this);
    }
    /**
     * @return the idViaje
     */
    public int getIdViaje() {
        return idViaje;
    }

    /**
     * @param idViaje the idViaje to set
     */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /**
     * @return the horaInicio
     */
    public Date getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFin
     */
    public Date getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getMatricula() {
        return this.vehiculo.getMatricula();
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getAlbaran() {
        return albaran;
    }

    public void setAlbaran(String albaran) {
        this.albaran = albaran;
    }

    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }
    
    
    
}
