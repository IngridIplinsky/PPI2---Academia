package com.example.academia.repository;

import com.example.academia.model.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

}