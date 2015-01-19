package br.com.ufu.bsi.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ufu.bsi.dto.Mensagem;
import br.com.ufu.bsi.dto.Usuario;

public interface MensagemDAO extends JpaRepository<Mensagem, Integer> {

	// SELECT * FROM `mensagens` order by data_mensagem desc
	List<Mensagem> findByStatusOrderByDataMensagemDesc(String status);

	List<Mensagem> findByUsuario(Usuario usuario);
	
	@Query(value = "SELECT 	COUNT(*) FROM mensagens WHERE lida = 0 and id_varejista = ?1 ", nativeQuery = true)
	BigInteger countQuantidadeMensagemNaoLidas(Integer idVarejista);
}
