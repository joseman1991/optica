/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

public class Citas {

    private int idcitas;
    private String observacion;
    private String codtiporesponsable;
    private String identificacion;
    private Date fecha;

    public int getIdcitas() {
        return idcitas;
    }

    public void setIdcitas(int idcitas) {
        this.idcitas = idcitas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCodtiporesponsable() {
        return codtiporesponsable;
    }

    public void setCodtiporesponsable(String codtiporesponsable) {
        this.codtiporesponsable = codtiporesponsable;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    

}
