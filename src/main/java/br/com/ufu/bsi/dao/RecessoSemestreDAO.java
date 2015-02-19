package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufu.bsi.dto.RecessoSemestre;
import br.com.ufu.bsi.dto.Semestre;

public interface RecessoSemestreDAO extends JpaRepository<RecessoSemestre, Integer> {

	List<RecessoSemestre> findBySemestre(Semestre s);
}
