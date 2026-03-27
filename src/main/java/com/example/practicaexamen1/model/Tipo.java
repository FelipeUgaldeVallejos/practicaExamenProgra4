package com.example.practicaexamen1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo", schema = "certificaciones")
public class Tipo {
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}