package br.com.ufu.bsi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.SemestreDAO;
import br.com.ufu.bsi.dto.Semestre;
import br.com.ufu.bsi.service.SemestreService;

@Service(value = "semestreService")
public class SemestreServiceImpl extends GenericServiceImpl<Semestre> implements SemestreService {

	@Autowired
	private SemestreDAO semestreDAO;
	
	@Override
	protected JpaRepository<Semestre, Integer> getRepository() {
		return semestreDAO;
	}
}
