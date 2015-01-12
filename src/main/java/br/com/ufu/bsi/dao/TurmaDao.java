package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ufu.bsi.dto.Turma;

public interface TurmaDao extends JpaRepository<Turma, Integer> {

	@Query(value = "select t.* from turmas t inner join disciplinas d on t.id_disciplina = d.id_disciplina where d.periodo = ?1 ", nativeQuery = true)
	List<Turma> findByPeriodo(Integer Periodo);
	
	Turma findByCodigoTurma(String codTurma);
}
