package br.com.ufu.bsi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.ProfessorTurmaDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.ProfessorTurma;
import br.com.ufu.bsi.service.ProfessorTurmaService;

@Service(value = "professorTurmaService")
public class ProfessorTurmaServiceImpl extends GenericServiceImpl<ProfessorTurma> implements ProfessorTurmaService {

	@Autowired
	private ProfessorTurmaDAO professorTurmaDAO;
	
	@Override
	protected JpaRepository<ProfessorTurma, Integer> getRepository() {
		return professorTurmaDAO;
	}
	
	public List<ProfessorTurma> findByProfessor(Professor professor) throws SiscordGenericException {
		try {
			return professorTurmaDAO.findByProfessor(professor);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public List<ProfessorTurma> findByAllTurmas() throws SiscordGenericException {
		try {
			return professorTurmaDAO.findByAllTurmas();
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}

	public void setProfessorTurmaDAO(ProfessorTurmaDAO professorTurmaDAO) {
		this.professorTurmaDAO = professorTurmaDAO;
	}
	
}
