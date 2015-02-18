package br.com.ufu.bsi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.RecessoSemestreDAO;
import br.com.ufu.bsi.dto.RecessoSemestre;
import br.com.ufu.bsi.service.RecessoSemestreService;

@Service(value = "recessoSemestreService")
public class RecessoSemestreServiceImpl extends GenericServiceImpl<RecessoSemestre> implements RecessoSemestreService {

	@Autowired
	private RecessoSemestreDAO recessoSemestreDAO;
	
	@Override
	protected JpaRepository<RecessoSemestre, Integer> getRepository() {
		return recessoSemestreDAO;
	}

	public void setRecessoSemestreDAO(RecessoSemestreDAO recessoSemestreDAO) {
		this.recessoSemestreDAO = recessoSemestreDAO;
	}

}