package br.com.ufu.bsi.serviceImpl;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.PlanoDisciplinaDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.PlanoDisciplina;
import br.com.ufu.bsi.service.PlanoDisciplinaService;

@Service(value = "planoDisciplinaService")
public class PlanoDisciplinaServiceImpl extends GenericServiceImpl<PlanoDisciplina> implements PlanoDisciplinaService {

	@Autowired
	private PlanoDisciplinaDAO planoDisciplinaDAO;
	
	@Override
	protected JpaRepository<PlanoDisciplina, Integer> getRepository() {
		return planoDisciplinaDAO;
	}
	
	public String findByDataDisciplina(String data, Integer adicionarDias) throws SiscordGenericException {
		try {
			return planoDisciplinaDAO.findByDataDisciplina(data, adicionarDias);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public int findByDiferencaEntreDatas(String data1, String data2) throws SiscordGenericException {
		try {
			BigInteger bi = planoDisciplinaDAO.findByDiferencaEntreDatas(data1, data2);
			return bi.intValue();
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}

	public void setPlanoDisciplinaDAO(PlanoDisciplinaDAO planoDisciplinaDAO) {
		this.planoDisciplinaDAO = planoDisciplinaDAO;
	}

}
