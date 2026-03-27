package com.example.practicaexamen1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "linea", schema = "certificaciones")
public class Linea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "documento", nullable = false)
    private Documento documento;

    @Column(name = "cantidad")
    private Integer cantidad;

    // Requerido por JPA
    public Linea() {
    }

    // Constructor para new Linea(usuario, documento, cantidad)
    public Linea(Usuario usuario, Documento documento, int cantidad) {
        this.usuario = usuario;
        this.documento = documento;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getMontoTotal() {
        if (documento != null && documento.getMonto() != null && cantidad != null) {
            return documento.getMonto() * cantidad;
        }
        return 0f;
    }
}