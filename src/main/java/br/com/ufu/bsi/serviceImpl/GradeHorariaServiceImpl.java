package br.com.ufu.bsi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.GradeHorariaDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.GradeHoraria;
import br.com.ufu.bsi.service.GradeHorariaService;

@Service(value = "gradeHorariaService")
public class GradeHorariaServiceImpl extends GenericServiceImpl<GradeHoraria> implements GradeHorariaService {

	@Autowired
	private GradeHorariaDAO gradeHorariaDAO;
	
	@Override
	protected JpaRepository<GradeHoraria, Integer> getRepository() {
		return gradeHorariaDAO;
	}

	public List<GradeHoraria> findByAluno(Aluno aluno) throws SiscordGenericException {
		try {
			return gradeHorariaDAO.findByAluno(aluno);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public void setGradeHorariaDAO(GradeHorariaDAO gradeHorariaDAO) {
		this.gradeHorariaDAO = gradeHorariaDAO;
	}
	
}
