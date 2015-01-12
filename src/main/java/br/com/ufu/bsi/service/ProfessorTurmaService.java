package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.ProfessorTurma;

public interface ProfessorTurmaService extends GenericService<ProfessorTurma> {

	List<ProfessorTurma> findByProfessor(Professor professor) throws SiscordGenericException;
	
	List<ProfessorTurma> findByAllTurmas() throws SiscordGenericException;
}
