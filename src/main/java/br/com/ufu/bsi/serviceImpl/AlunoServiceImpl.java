package br.com.ufu.bsi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.AlunoDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.Usuario;
import br.com.ufu.bsi.service.AlunoService;

@Service(value = "alunoService")
public class AlunoServiceImpl extends GenericServiceImpl<Aluno> implements AlunoService {

	@Autowired
	private AlunoDAO alunoDAO;
	
	@Override
	protected JpaRepository<Aluno, Integer> getRepository() {
		return alunoDAO;
	}

	public Aluno findByUsuario(Usuario usuario) throws SiscordGenericException {
		try {
			return alunoDAO.findByUsuario(usuario);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public List<Aluno> findBySimulacaoAjuste(Boolean gradeEnviada) throws SiscordGenericException {
		try {
			return alunoDAO.findBySimulacaoAjuste(gradeEnviada);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public void setAlunoDAO(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

}
