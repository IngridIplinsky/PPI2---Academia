package com.example.academia.model;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "Usuarios")
@Table(name = "usuarios")
@Getter
@Setter
@EqualsAndHashCode(of = "id")

public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String contato;
    private String imagem;
    private String plano;
    private String validade;

    public Usuarios(){}

    public Usuarios(Long id, String nome, String email, String senha, String contato, String imagem, String plano, String validade){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.contato = contato;
        this.imagem = imagem;
        this.plano = plano;
        this.validade = validade;
    }

    /*public Usuarios(String nome, String email, String senha, String contato, String imagem, String plano, String validade){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.contato = contato;
        this.imagem = imagem;
        this.plano = plano;
        this.validade = validade;
    }*/
}
