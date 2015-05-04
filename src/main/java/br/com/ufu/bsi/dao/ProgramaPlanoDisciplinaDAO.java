package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.ProgramaPlanoDisciplina;
import br.com.ufu.bsi.queries.ProgramaPlanoDisciplinaQueries;

public interface ProgramaPlanoDisciplinaDAO extends JpaRepository<ProgramaPlanoDisciplina, Integer> {

	List<ProgramaPlanoDisciplina> findByDisciplina(Disciplina disciplina);
	
	List<ProgramaPlanoDisciplina> findByPlanoDisciplinaStatus(Character status);
	
	List<ProgramaPlanoDisciplina> findByDisciplinaAndPlanoDisciplinaStatus(Disciplina disciplina, Character status);
	
	List<ProgramaPlanoDisciplina> findByDisciplinaProfessorAndPlanoDisciplina(Professor professor, Character status);
	
	@Query(value = ProgramaPlanoDisciplinaQueries.findByStatusReprovado, nativeQuery = true)
	List<ProgramaPlanoDisciplina> findByStatusReprovado(Professor professor, Character status1, Character status2);
}
