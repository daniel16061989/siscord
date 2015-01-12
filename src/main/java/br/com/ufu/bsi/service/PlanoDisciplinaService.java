package br.com.ufu.bsi.service;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.PlanoDisciplina;

public interface PlanoDisciplinaService extends GenericService<PlanoDisciplina> {

	String findByDataDisciplina(String data, Integer adicionarDias) throws SiscordGenericException;
	
	int findByDiferencaEntreDatas(String data1, String data2) throws SiscordGenericException;
}
