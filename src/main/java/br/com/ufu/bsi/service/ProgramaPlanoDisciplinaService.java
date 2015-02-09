package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.ProgramaPlanoDisciplina;

public interface ProgramaPlanoDisciplinaService extends GenericService<ProgramaPlanoDisciplina> {

	List<ProgramaPlanoDisciplina> findByDisciplina(Disciplina disciplina) throws SiscordGenericException;
	
	List<ProgramaPlanoDisciplina> findByPlanoDisciplinaStatus(Character status) throws SiscordGenericException;
	
	List<ProgramaPlanoDisciplina> findByDisciplinaAndPlanoDisciplinaStatus(Disciplina disciplina, Character status) throws SiscordGenericException;
	
	List<ProgramaPlanoDisciplina> findByDisciplinaProfessorAndPlanoDisciplina(Professor professor, Character status) throws SiscordGenericException;
}
