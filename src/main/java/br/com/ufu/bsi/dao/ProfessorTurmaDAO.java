package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.ProfessorTurma;
import br.com.ufu.bsi.queries.ProfessorTurmaQueries;

public interface ProfessorTurmaDAO extends JpaRepository<ProfessorTurma, Integer> {

	List<ProfessorTurma> findByProfessor(Professor professor);
	
	@Query(value = ProfessorTurmaQueries.FIND_BY_ALL, nativeQuery = true)
	List<ProfessorTurma> findByAllTurmas();
}
