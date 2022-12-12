package com.example.usuariocrud.params;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UsuarioParams {

    @NotNull
    private String codigo;

    @NotNull
    private String nome;

    private LocalDate dataNascimento;

    public UsuarioParams() {
    }

}
