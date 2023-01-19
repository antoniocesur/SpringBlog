package com.vedoque.notas.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String titulo;
    private String contenido;
    private LocalDate fecha;
    private String imagen;

    public Entrada(){
        this.fecha=LocalDate.now();
        this.imagen="vacia.jpg";
    }
}
