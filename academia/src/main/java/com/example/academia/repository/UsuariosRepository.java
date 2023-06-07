package com.example.academia.repository;

import com.example.academia.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "Usuarios", path = "Usuarios")
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    @Query(value = "SELECT CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END FROM usuarios WHERE id = :id ", nativeQuery = true)
    boolean exist(Long id);

    @Query(value = "select * from usuarios where email = ?1 and senha = ?2 ", nativeQuery = true)
    Usuarios Login(String email, String senha);
}