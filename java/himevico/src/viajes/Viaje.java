/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viajes;

import himevico.GestorBBDD;
import java.sql.SQLException;

/**
 *
 * @author 7FPROG10
 */
public class Viaje {
    
    private int idViaje;
    private int horaInicio;
    private int horaFin;
    private String matricula;

    public Viaje() {
    }

    public Viaje(int idViaje, int horaInicio, int horaFin, String matricula) {
        this.idViaje = idViaje;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.matricula = matricula;
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
    public int getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFin
     */
    public int getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    
    
}
