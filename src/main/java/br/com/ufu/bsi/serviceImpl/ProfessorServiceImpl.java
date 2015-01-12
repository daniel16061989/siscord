package br.com.ufu.bsi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.ProfessorDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.Usuario;
import br.com.ufu.bsi.service.ProfessorService;

@Service(value = "professorService")
public class ProfessorServiceImpl extends GenericServiceImpl<Professor> implements ProfessorService {

	@Autowired
	private ProfessorDAO professorDAO;
	
	@Override
	protected JpaRepository<Professor, Integer> getRepository() {
		return professorDAO;
	}
	
	public Professor findByUsuario(Usuario usuario) throws SiscordGenericException {
		try {
			return professorDAO.findByUsuario(usuario);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}

	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}

}
