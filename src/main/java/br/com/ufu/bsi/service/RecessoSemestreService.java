package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.RecessoSemestre;
import br.com.ufu.bsi.dto.Semestre;

public interface RecessoSemestreService extends GenericService<RecessoSemestre> {

	List<RecessoSemestre> findBySemestre(Semestre s) throws SiscordGenericException;
}
