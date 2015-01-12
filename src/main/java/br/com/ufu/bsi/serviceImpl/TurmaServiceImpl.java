package br.com.ufu.bsi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.TurmaDao;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Turma;
import br.com.ufu.bsi.service.TurmaService;

@Service(value = "turmaService")
public class TurmaServiceImpl extends GenericServiceImpl<Turma> implements TurmaService {
	
	@Autowired
	private TurmaDao turmaDao;
	
	@Override
	protected JpaRepository<Turma, Integer> getRepository() {
		return turmaDao;
	}

	public List<Turma> findByPeriodo(Integer Periodo) throws SiscordGenericException {
		try {
			return turmaDao.findByPeriodo(Periodo);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public Turma findByCodigoTurma(String codTurma) throws SiscordGenericException {
		try {
			return turmaDao.findByCodigoTurma(codTurma);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public void setTurmaDao(TurmaDao turmaDao) {
		this.turmaDao = turmaDao;
	}
	
}
