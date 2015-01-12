package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.PlanoDisciplina;
import br.com.ufu.bsi.dto.ProgramaDisciplina;

public interface ProgramaDisciplinaService extends GenericService<ProgramaDisciplina> {

	List<ProgramaDisciplina> findByPlanoDisciplina(PlanoDisciplina planoDisciplina) throws SiscordGenericException;
}
