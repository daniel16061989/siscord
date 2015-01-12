package br.com.ufu.bsi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ufu.bsi.dao.ReposicaoAulaDAO;
import br.com.ufu.bsi.dto.ReposicaoAula;
import br.com.ufu.bsi.service.ReposicaoAulaService;

@Service(value = "reposicaoAulaService")
public class ReposicaoAulaServiceImpl extends GenericServiceImpl<ReposicaoAula> implements ReposicaoAulaService {

	@Autowired
	private ReposicaoAulaDAO reposicaoAulaDAO;
	
	@Override
	protected JpaRepository<ReposicaoAula, Integer> getRepository() {
		return reposicaoAulaDAO;
	}

	public void setReposicaoAulaDAO(ReposicaoAulaDAO reposicaoAulaDAO) {
		this.reposicaoAulaDAO = reposicaoAulaDAO;
	}

}
