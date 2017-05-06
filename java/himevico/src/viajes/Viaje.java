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
import vehiculo.Vehiculo;

/**
 *
 * @author 7FPROG10
 */
public class Viaje {
    
    private int idViaje;
    private Date horaInicio;
    private Date horaFin;
    private vehiculo.Vehiculo vehiculo;
    private String albaran;

    public Viaje() {
    }

    public Viaje(Date horaInicio, Date horaFin, vehiculo.Vehiculo vehiculo, String albaran) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.vehiculo = vehiculo;
        this.albaran = albaran;

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
    
    
    
}
