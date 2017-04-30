/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partes;

/**
 *
 * @author 7FPROG10
 */
public class Parte {
    
    private int idParte;
    private double kilometrosInicio;
    private double kilometrosFin;
    private double gasoil;
    private double peajes;
    private double dietas;
    private double otros;
    private boolean eliminado;
    private boolean validado;

    
    public Parte() {
    }

    public Parte(int idParte, double kilometrosInicio, double kilometrosFin, double gasoil, double peajes, double dietas, double otros, boolean eliminado, boolean validado) {
        this.idParte = idParte;
        this.kilometrosInicio = kilometrosInicio;
        this.kilometrosFin = kilometrosFin;
        this.gasoil = gasoil;
        this.peajes = peajes;
        this.dietas = dietas;
        this.otros = otros;
        this.eliminado = eliminado;
        this.validado = validado;
    }

    /**
     * @return the idParte
     */
    public int getIdParte() {
        return idParte;
    }

    /**
     * @param idParte the idParte to set
     */
    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

    /**
     * @return the kilometrosInicio
     */
    public double getKilometrosInicio() {
        return kilometrosInicio;
    }

    /**
     * @param kilometrosInicio the kilometrosInicio to set
     */
    public void setKilometrosInicio(double kilometrosInicio) {
        this.kilometrosInicio = kilometrosInicio;
    }

    /**
     * @return the kilometrosFin
     */
    public double getKilometrosFin() {
        return kilometrosFin;
    }

    /**
     * @param kilometrosFin the kilometrosFin to set
     */
    public void setKilometrosFin(double kilometrosFin) {
        this.kilometrosFin = kilometrosFin;
    }

    /**
     * @return the gasoil
     */
    public double getGasoil() {
        return gasoil;
    }

    /**
     * @param gasoil the gasoil to set
     */
    public void setGasoil(double gasoil) {
        this.gasoil = gasoil;
    }

    /**
     * @return the peajes
     */
    public double getPeajes() {
        return peajes;
    }

    /**
     * @param peajes the peajes to set
     */
    public void setPeajes(double peajes) {
        this.peajes = peajes;
    }

    /**
     * @return the dietas
     */
    public double getDietas() {
        return dietas;
    }

    /**
     * @param dietas the dietas to set
     */
    public void setDietas(double dietas) {
        this.dietas = dietas;
    }

    /**
     * @return the otros
     */
    public double getOtros() {
        return otros;
    }

    /**
     * @param otros the otros to set
     */
    public void setOtros(double otros) {
        this.otros = otros;
    }

    /**
     * @return the eliminado
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * @return the validado
     */
    public boolean isValidado() {
        return validado;
    }

    /**
     * @param validado the validado to set
     */
    public void setValidado(boolean validado) {
        this.validado = validado;
    }
    
    
    
    
}
