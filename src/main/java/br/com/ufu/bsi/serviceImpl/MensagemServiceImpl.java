package br.com.ufu.bsi.serviceImpl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.MensagemDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Mensagem;
import br.com.ufu.bsi.dto.Usuario;
import br.com.ufu.bsi.service.MensagemService;

@Service(value = "mensagemService")
public class MensagemServiceImpl extends GenericServiceImpl<Mensagem> implements MensagemService {

	@Autowired
	private MensagemDAO mensagemDAO;
	
	@Override
	protected JpaRepository<Mensagem, Integer> getRepository() {
		return mensagemDAO;
	}

	public List<Mensagem> findByStatus(String status) throws SiscordGenericException {
		try {
			return mensagemDAO.findByStatusOrderByDataMensagemDesc(status);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public List<Mensagem> findByUsuario(Usuario usuario) throws SiscordGenericException {
		try {
			return mensagemDAO.findByUsuario(usuario);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public Integer countQuantidadeMensagemNaoLidas(Integer idVarejista) throws SiscordGenericException {
		try {
			BigInteger value = mensagemDAO.countQuantidadeMensagemNaoLidas(idVarejista);
			return value.intValue();
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public void setMensagemDAO(MensagemDAO mensagemDAO) {
		this.mensagemDAO = mensagemDAO;
	}

}