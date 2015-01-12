package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Mensagem;
import br.com.ufu.bsi.dto.Usuario;

public interface MensagemService extends GenericService<Mensagem> {

	List<Mensagem> findByStatus(String status) throws SiscordGenericException;
	
	List<Mensagem> findByUsuario(Usuario usuario) throws SiscordGenericException;
	
	Integer countQuantidadeMensagemNaoLidas(Integer idVarejista) throws SiscordGenericException;
}
