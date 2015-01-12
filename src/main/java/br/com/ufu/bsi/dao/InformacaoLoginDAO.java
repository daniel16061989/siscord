package br.com.ufu.bsi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufu.bsi.dto.InformacaoLogin;

public interface InformacaoLoginDAO extends JpaRepository<InformacaoLogin, Integer> {

}
