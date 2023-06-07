package com.example.academia.repository;

import com.example.academia.model.Horarios;
import com.example.academia.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@RepositoryRestResource(collectionResourceRel = "Horarios", path = "Horarios")
public interface HorariosRepository extends JpaRepository<Horarios, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update horarios set vaga = ?2 where id = ?1", nativeQuery = true)
    void Agendar(Long id, int vaga);

    @Query(value = "select * from horarios where aula = ?1 and horario = ?2", nativeQuery = true)
    Horarios PesquisarVagaAula(String aula, Time horario);
}
