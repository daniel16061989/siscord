package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.ProgramaPlanoDisciplina;

public interface ProgramaPlanoDisciplinaService extends GenericService<ProgramaPlanoDisciplina> {

	List<ProgramaPlanoDisciplina> findByDisciplina(Disciplina disciplina) throws SiscordGenericException;
}
