package br.com.ufu.bsi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.Usuario;

public interface ProfessorDAO extends JpaRepository<Professor, Integer> {

	Professor findByUsuario(Usuario usuario);
}
