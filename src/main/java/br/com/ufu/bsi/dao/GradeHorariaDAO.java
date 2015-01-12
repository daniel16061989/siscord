package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.GradeHoraria;

public interface GradeHorariaDAO extends JpaRepository<GradeHoraria, Integer> {

	List<GradeHoraria> findByAluno(Aluno aluno);
}
