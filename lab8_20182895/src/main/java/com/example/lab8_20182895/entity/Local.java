package com.example.lab8_20182895.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "local")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "direccion", length = 45)
    private String direccion;

    @Column(name = "latitud", length = 45)
    private String latitud;

    @Column(name = "longitud", length = 45)
    private String longitud;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa idEmpresa;

}