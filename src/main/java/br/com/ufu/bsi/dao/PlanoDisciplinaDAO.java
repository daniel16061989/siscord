package br.com.ufu.bsi.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ufu.bsi.dto.PlanoDisciplina;

public interface PlanoDisciplinaDAO extends JpaRepository<PlanoDisciplina, Integer> {

	@Query(value = "SELECT DATE_ADD(?1, INTERVAL ?2 DAY) ", nativeQuery = true)
	String findByDataDisciplina(String data, Integer adicionarDias);
	
	@Query(value = "SELECT DATEDIFF(?1, ?2) ", nativeQuery = true)
	BigInteger findByDiferencaEntreDatas(String data1, String data2);
}
