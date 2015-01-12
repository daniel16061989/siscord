package br.com.ufu.bsi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.ProgramaPlanoDisciplinaDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.ProgramaPlanoDisciplina;
import br.com.ufu.bsi.service.ProgramaPlanoDisciplinaService;

@Service(value = "programaPlanoDisciplinaService")
public class ProgramaPlanoDisciplinaServiceImpl extends GenericServiceImpl<ProgramaPlanoDisciplina> implements ProgramaPlanoDisciplinaService {

	@Autowired
	private ProgramaPlanoDisciplinaDAO programaPlanoDisciplinaDAO;
	
	@Override
	protected JpaRepository<ProgramaPlanoDisciplina, Integer> getRepository() {
		return programaPlanoDisciplinaDAO;
	}

	public List<ProgramaPlanoDisciplina> findByDisciplina(Disciplina disciplina) throws SiscordGenericException {
		try {
			return programaPlanoDisciplinaDAO.findByDisciplina(disciplina);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public void setProgramaPlanoDisciplinaDAO(ProgramaPlanoDisciplinaDAO programaPlanoDisciplinaDAO) {
		this.programaPlanoDisciplinaDAO = programaPlanoDisciplinaDAO;
	}
	
}
