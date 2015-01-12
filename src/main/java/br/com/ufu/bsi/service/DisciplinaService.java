package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.Turma;

public interface DisciplinaService extends GenericService<Disciplina> {

	Disciplina findByCodigoDisciplina(String codigoDisciplina) throws SiscordGenericException;
	
	List<Disciplina> findByPeriodo(Integer periodo) throws SiscordGenericException;
	
	Disciplina findByCodigoDisciplinaAndTurma(String codigoDisciplina, Turma turma) throws SiscordGenericException;
	
	List<Disciplina> findByProfessor(Professor professor) throws SiscordGenericException;
}
