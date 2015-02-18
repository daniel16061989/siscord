package br.com.ufu.bsi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.SemestreDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Semestre;
import br.com.ufu.bsi.service.SemestreService;

@Service(value = "semestreService")
public class SemestreServiceImpl extends GenericServiceImpl<Semestre> implements SemestreService {

	@Autowired
	private SemestreDAO semestreDAO;
	
	public List<Semestre> findByData() throws SiscordGenericException {
		try {
			return semestreDAO.findByData();
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	@Override
	protected JpaRepository<Semestre, Integer> getRepository() {
		return semestreDAO;
	}
}
