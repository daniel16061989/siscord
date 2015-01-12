package br.com.ufu.bsi.queries;

public class ProfessorTurmaQueries {

	public static final String FIND_BY_ALL =
			"SELECT pt.* FROM disciplinas d inner join turmas t on d.id_disciplina = t.id_disciplina " +
			"							 left join professores_turmas pt on t.id_turma = pt.id_turma " +
			"							 left join professores p on pt.id_professor = p.id_professor ";
}
