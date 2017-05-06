/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viajes;

import himevico.GestorBBDD;
import java.sql.SQLException;
import java.sql.Time;

/**
 *
 * @author 7FPROG10
 */
public class Viaje {
    
    private int idViaje;
    private Time horaInicio;
    private Time horaFin;
    private vehiculo.Vehiculo vehiculo;

    public Viaje() {
    }

    public Viaje(Time horaInicio, Time horaFin, vehiculo.Vehiculo vehiculo) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.vehiculo = vehiculo;
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
    public Time getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFin
     */
    public Time getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public String getMatricula() {
        return this.vehiculo.getMatricula();
    }
    
    
}
