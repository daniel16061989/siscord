package br.com.ufu.bsi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.RecessoSemestreDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.RecessoSemestre;
import br.com.ufu.bsi.dto.Semestre;
import br.com.ufu.bsi.service.RecessoSemestreService;

@Service(value = "recessoSemestreService")
public class RecessoSemestreServiceImpl extends GenericServiceImpl<RecessoSemestre> implements RecessoSemestreService {

	@Autowired
	private RecessoSemestreDAO recessoSemestreDAO;
	
	@Override
	protected JpaRepository<RecessoSemestre, Integer> getRepository() {
		return recessoSemestreDAO;
	}
	
	public List<RecessoSemestre> findBySemestre(Semestre s) throws SiscordGenericException {
		try {
			return recessoSemestreDAO.findBySemestre(s);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}

	public void setRecessoSemestreDAO(RecessoSemestreDAO recessoSemestreDAO) {
		this.recessoSemestreDAO = recessoSemestreDAO;
	}

}