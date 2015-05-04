package br.com.ufu.bsi.queries;

public class ProgramaPlanoDisciplinaQueries {

	public static final String findByStatusReprovado = 
			"SELECT ppd.* FROM programas_planos_disciplinas ppd " +
			"		  inner join planos_disciplinas pd on ppd.id_plano_disciplina = pd.id_plano_disciplina " +
			"		  where pd.id_professor = ?1 " +
			"		  AND pd.status in (?2, ?3) ";
}