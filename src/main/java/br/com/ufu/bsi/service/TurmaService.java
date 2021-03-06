package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Turma;

public interface TurmaService extends GenericService<Turma> {

	List<Turma> findByPeriodo(Integer Periodo) throws SiscordGenericException;
	
	Turma findByCodigoTurma(String codTurma) throws SiscordGenericException;
}
