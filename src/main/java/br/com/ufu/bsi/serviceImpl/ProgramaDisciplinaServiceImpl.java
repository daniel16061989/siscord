package br.com.ufu.bsi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.ProgramaDisciplinaDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.PlanoDisciplina;
import br.com.ufu.bsi.dto.ProgramaDisciplina;
import br.com.ufu.bsi.service.ProgramaDisciplinaService;

@Service(value = "programaDisciplinaService")
public class ProgramaDisciplinaServiceImpl extends GenericServiceImpl<ProgramaDisciplina> implements ProgramaDisciplinaService {

	@Autowired
	private ProgramaDisciplinaDAO programaDisciplinaDAO;
	
	@Override
	protected JpaRepository<ProgramaDisciplina, Integer> getRepository() {
		return programaDisciplinaDAO;
	}
	
	public List<ProgramaDisciplina> findByPlanoDisciplina(PlanoDisciplina planoDisciplina) throws SiscordGenericException {
		try {
			return programaDisciplinaDAO.findByPlanoDisciplina(planoDisciplina);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}

	public void setProgramaDisciplinaDAO(ProgramaDisciplinaDAO programaDisciplinaDAO) {
		this.programaDisciplinaDAO = programaDisciplinaDAO;
	}
	

}
