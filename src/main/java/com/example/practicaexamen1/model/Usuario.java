package com.example.practicaexamen1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario", schema = "certificaciones")
public class Usuario {
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "clave", nullable = false, length = 100)
    private String clave;

    @Column(name = "rol", nullable = false, length = 10)
    private String rol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}