package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.ProgramaPlanoDisciplina;

public interface ProgramaPlanoDisciplinaDAO extends JpaRepository<ProgramaPlanoDisciplina, Integer> {

	List<ProgramaPlanoDisciplina> findByDisciplina(Disciplina disciplina);
	
	List<ProgramaPlanoDisciplina> findByPlanoDisciplinaStatus(Character status);
	
	List<ProgramaPlanoDisciplina> findByDisciplinaAndPlanoDisciplinaStatus(Disciplina disciplina, Character status);
}
