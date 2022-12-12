package com.example.usuariocrud.repository;

import com.example.usuariocrud.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findFirstByCodigo(String codigo);

}
