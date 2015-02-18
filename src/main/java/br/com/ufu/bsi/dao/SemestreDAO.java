package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ufu.bsi.dto.Semestre;

public interface SemestreDAO extends JpaRepository<Semestre, Integer> {

	@Query(value = "select * from semestres where now() between dataInicio and dataFim", nativeQuery = true)
	List<Semestre> findByData();
}
