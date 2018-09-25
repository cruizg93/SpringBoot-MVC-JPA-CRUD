package com.cristianruizblog.repository;

import org.springframework.data.repository.CrudRepository;

import com.cristianruizblog.Entity.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

}
