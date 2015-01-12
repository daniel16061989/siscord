package br.com.ufu.bsi.service;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.Usuario;

public interface ProfessorService extends GenericService<Professor> {

	Professor findByUsuario(Usuario usuario) throws SiscordGenericException;
}
