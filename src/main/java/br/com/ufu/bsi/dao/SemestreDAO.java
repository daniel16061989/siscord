package br.com.ufu.bsi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufu.bsi.dto.Semestre;

public interface SemestreDAO extends JpaRepository<Semestre, Integer> {

}
