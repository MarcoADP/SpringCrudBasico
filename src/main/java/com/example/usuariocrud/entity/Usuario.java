package com.example.usuariocrud.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Where(clause = "removido=false")
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String codigo;

    @NotNull
    private String nome;

    private LocalDate dataNascimento;

    @Setter
    private String foto;

    private Boolean removido = false;

    public Usuario() {
    }

    public Usuario(String codigo, String nome, LocalDate dataNascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public void update(String codigo, String nome, LocalDate dataNascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public void remove() {
        this.removido = true;
    }

    public void restore() {
        this.removido = false;
    }

}
