package com.caschile.horus.model;

import java.util.List;

public class CajaDTO {
    public String name;
    public Integer caja;
    public List<Integer> data;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getCaja() { return caja; }
    public void setCaja(Integer caja) { this.caja = caja; }

    public List<Integer> getData() {
        return data;
    }
    public void setData(List<Integer> data) {
        this.data = data;
    }
}
