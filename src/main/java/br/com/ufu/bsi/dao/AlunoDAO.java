package br.com.ufu.bsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.Usuario;

public interface AlunoDAO extends JpaRepository<Aluno, Integer> {

	Aluno findByUsuario(Usuario usuario);
	
	List<Aluno> findBySimulacaoAjuste(Boolean gradeEnviada);
	
	List<Aluno> findByMatricula(String matricula);
}
