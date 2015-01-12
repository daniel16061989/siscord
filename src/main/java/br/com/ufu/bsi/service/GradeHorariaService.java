package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.GradeHoraria;

public interface GradeHorariaService extends GenericService<GradeHoraria> {

	List<GradeHoraria> findByAluno(Aluno aluno) throws SiscordGenericException;
}
