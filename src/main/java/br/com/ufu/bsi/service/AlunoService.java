package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.Usuario;

public interface AlunoService extends GenericService<Aluno> {

	Aluno findByUsuario(Usuario usuario) throws SiscordGenericException;
	
	List<Aluno> findBySimulacaoAjuste(Boolean gradeEnviada) throws SiscordGenericException;
}
