package com.example.academia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.sql.Time;
import java.util.Timer;

@Entity(name = "Horarios")
@Table(name = "horarios")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Horarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String aula;

    @Column(nullable = false)
    private String professor;

    @Column(nullable = false)
    private Time horario;

    @Column(nullable = false)
    private int vaga;

    public Horarios(String aula, String professor, Time horario, int vaga){
        this.aula = aula;
        this.professor = professor;
        this.horario = horario;
        this.vaga = vaga;
    }

    public Horarios(){}
}
