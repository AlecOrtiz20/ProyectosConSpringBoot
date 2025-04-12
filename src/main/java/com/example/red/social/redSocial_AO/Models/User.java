/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.red.social.redSocial_AO.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 *
 * @author DELL
 */

@Getter
@Setter
@Entity
@Table(name = "usuario")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String username;

    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;

    private String bio_usuario;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;




}


