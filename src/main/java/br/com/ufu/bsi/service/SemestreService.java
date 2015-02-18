package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Semestre;

public interface SemestreService extends GenericService<Semestre>{

	List<Semestre> findByData() throws SiscordGenericException;
}
