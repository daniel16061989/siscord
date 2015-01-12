package br.com.ufu.bsi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.DisciplinaDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.Turma;
import br.com.ufu.bsi.service.DisciplinaService;

@Service(value = "disciplinaService")
public class DisciplinaServiceImpl extends GenericServiceImpl<Disciplina> implements DisciplinaService {

	@Autowired
	private DisciplinaDAO disciplinaDAO;
	
	@Override
	protected JpaRepository<Disciplina, Integer> getRepository() {
		return disciplinaDAO;
	}
	
	public Disciplina findByCodigoDisciplina(String codigoDisciplina) throws SiscordGenericException {
		try {
			return disciplinaDAO.findByCodigoDisciplina(codigoDisciplina);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}

	public List<Disciplina> findByPeriodo(Integer periodo) throws SiscordGenericException {
		try {
			return disciplinaDAO.findByPeriodo(periodo);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public Disciplina findByCodigoDisciplinaAndTurma(String codigoDisciplina, Turma turma) throws SiscordGenericException {
		try {
			return disciplinaDAO.findByCodigoDisciplinaAndTurma(codigoDisciplina, turma);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public List<Disciplina> findByProfessor(Professor professor) throws SiscordGenericException {
		try {
			return disciplinaDAO.findByProfessor(professor);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}

}
