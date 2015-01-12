package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.Turma;

public interface DisciplinaDAO extends JpaRepository<Disciplina, Integer> {

	Disciplina findByCodigoDisciplina(String codigoDisciplina);
	
	Disciplina findByCodigoDisciplinaAndTurma(String codigoDisciplina, Turma turma);
	
	List<Disciplina> findByPeriodo(Integer periodo);
	
	List<Disciplina> findByProfessor(Professor professor);
}
