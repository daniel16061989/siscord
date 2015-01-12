package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufu.bsi.dto.PlanoDisciplina;
import br.com.ufu.bsi.dto.ProgramaDisciplina;

public interface ProgramaDisciplinaDAO extends JpaRepository<ProgramaDisciplina, Integer> {

	List<ProgramaDisciplina> findByPlanoDisciplina(PlanoDisciplina planoDisciplina);
}
