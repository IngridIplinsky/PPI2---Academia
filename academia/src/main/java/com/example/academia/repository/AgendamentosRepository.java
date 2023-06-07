package com.example.academia.repository;

import com.example.academia.model.Agendamentos;
import com.example.academia.model.Horarios;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@RepositoryRestResource(collectionResourceRel = "Agendamentos", path = "Agendamentos")
public interface AgendamentosRepository extends JpaRepository<Agendamentos, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into agendamentos(email, aula, horario, dia) values(?1, ?2, ?3, ?4)", nativeQuery = true)
    void Marcar(String email, String aula, Time horario, Date dia);

    @Query(value = "select * from agendamentos where email = ?1 and horario = ?2 and dia = ?3", nativeQuery = true)
    Agendamentos Pesquisar(String email, Time horario, Date dia);

    @Query(value = "select * from agendamentos where email = ?1 and dia = ?2", nativeQuery = true)
    Collection<Agendamentos> PesquisarUser(String email, Date dia);
}
