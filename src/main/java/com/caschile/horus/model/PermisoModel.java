package com.caschile.horus.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Permisos_de_Circulacion")
public class PermisoModel {

    @Id @Column(name="Placa", insertable=false, updatable = false)
    private String placa;

    @Column(name="Año_del_Permiso", insertable = false, updatable = false)
    private Integer añoDelPermiso;

    @Column(name="Numero_Caja", insertable = false, updatable = false)
    private Integer numeroCaja;

    @Column(name="Total_a_Pagar", insertable = false, updatable = false)
    private Integer totalAPagar;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getAñoDelPermiso() {
        return añoDelPermiso;
    }

    public void setAñoDelPermiso(Integer añoDelPermiso) {
        this.añoDelPermiso = añoDelPermiso;
    }

    public Integer getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(Integer numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    public Integer getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(Integer totalAPagar) {
        this.totalAPagar = totalAPagar;
    }
}
