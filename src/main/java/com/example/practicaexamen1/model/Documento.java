package com.example.practicaexamen1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "documento", schema = "certificaciones")
public class Documento {
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    @Column(name = "monto")
    private Float monto;

    @Column(name = "timbres")
    private Float timbres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo")
    private Tipo tipo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Float getTimbres() {
        return timbres;
    }

    public void setTimbres(Float timbres) {
        this.timbres = timbres;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Float getPrecioTotal() {
        float m = (monto != null) ? monto : 0f;
        float t = (timbres != null) ? timbres : 0f;
        return m + t;
    }
}