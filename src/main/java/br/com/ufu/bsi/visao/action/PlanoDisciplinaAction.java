package br.com.ufu.bsi.visao.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.PlanoDisciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.ProgramaDisciplina;
import br.com.ufu.bsi.dto.ProgramaPlanoDisciplina;
import br.com.ufu.bsi.dto.Semestre;

@ParentPackage("default")
@InterceptorRef("professor")
@Namespace(value="/planoDisciplina")
public class PlanoDisciplinaAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private List<Disciplina> professorDisciplinas;
	
	private List<ProgramaPlanoDisciplina> programaPlanoDisciplinasProfessor;

	@Action(value = "index", results = {@Result(name = "success", location = "/professor/planoDisciplina.jsp")})
	public String index() {
		Professor professor = new Professor();
		professorDisciplinas = new ArrayList<Disciplina>();
		programaPlanoDisciplinasProfessor = new ArrayList<ProgramaPlanoDisciplina>();

		try {
			professor = professorService.findByUsuario(usuarioLogado.getUsuario());
			professorDisciplinas = disciplinaService.findByProfessor(professor);
			programaPlanoDisciplinasProfessor = programaPlanoDisciplinaService.findByDisciplinaProfessorAndPlanoDisciplina(professor, PlanoDisciplina.STATUS_FINAL);
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	@Action(value = "buscarPlanoDisciplinaProfessor", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
														@Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarPlanoDisciplinaProfessor() {
		String idProgramaPlanoDisciplina = request.getParameter("idProgramaPlanoDisciplina");
		Gson gson = new Gson();
		String jsonProgramaPlanoDisciplina = "";
		
		ProgramaPlanoDisciplina ppd = new ProgramaPlanoDisciplina();
		try {
			ppd = programaPlanoDisciplinaService.findOne(Integer.valueOf(idProgramaPlanoDisciplina));
			jsonProgramaPlanoDisciplina = gson.toJson(ppd);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", jsonProgramaPlanoDisciplina);
		return SUCCESS;
	}
	

	@Action(value = "salvarPlanoDisciplina", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
					 						 			@Result(name="error", type="json", params = {"root","jsonData"})})
	public String salvarPlanoDisciplina() {
		String idDisciplina = request.getParameter("disciplina");
		String metodologia = request.getParameter("metodologia");
		String avaliacao = request.getParameter("avaliacao");
		String atendimento = request.getParameter("atendimento");
		String recuperacao = request.getParameter("recuperacao");
		String descricaoDia = request.getParameter("descricaoDia");
		String idPlanoDisciplina = request.getParameter("idPlanoDisciplina");
		
		String conteudoAula[] = descricaoDia.split(";");  
		
		try {
			Professor professor = new Professor();
			professor = professorService.findByUsuario(usuarioLogado.getUsuario());
		
			Disciplina disciplina = new Disciplina();
			disciplina = disciplinaService.findOne(Integer.valueOf(idDisciplina));
			 
			List<Semestre> semestre = new ArrayList<Semestre>();
			semestre = semestreService.findAll();

			List<Date> diasAula = new ArrayList<Date>();
			if(semestre.size() > 0) {
				diasAula = diasDisciplina(semestre.get(0).getDataInicio(), semestre.get(0).getDataFim(), disciplina);
			}
			
			// plano de disciplina
			PlanoDisciplina planoDisciplina = new PlanoDisciplina();
			
			if(idPlanoDisciplina != null) {
				if(!(idDisciplina.trim().equals(""))) {
//					planoDisciplina.setIdPlanoDisciplina(Integer.parseInt(idPlanoDisciplina));
					
					planoDisciplina = planoDisciplinaService.findOne(Integer.parseInt(idPlanoDisciplina));
					
					if(planoDisciplina.getStatus().equals(PlanoDisciplina.STATUS_VOLTAR_COLEGIADO)) {
						planoDisciplina.setStatus(PlanoDisciplina.STATUS_COLEGIADO);
					} else {
						planoDisciplina.setStatus(PlanoDisciplina.STATUS_COORDENADOR);
					}
				} else {
					planoDisciplina.setStatus(PlanoDisciplina.STATUS_COLEGIADO);
				}
			} else {
				planoDisciplina.setStatus(PlanoDisciplina.STATUS_COLEGIADO);
			}
			
			planoDisciplina.setProfessor(professor);
			planoDisciplina.setAtendimento(atendimento);
			planoDisciplina.setAvaliacao(avaliacao);
			planoDisciplina.setMetodologia(metodologia);
			planoDisciplina.setRecuperacao(recuperacao);

			planoDisciplina = planoDisciplinaService.save(planoDisciplina);
			
			// programa de disciplina de todas as aulas do semestre
			for(int i = 0; i < conteudoAula.length; i++) {
				ProgramaDisciplina programaDisciplina = new ProgramaDisciplina();
				
				programaDisciplina.setConteudoAula(conteudoAula[i]);
				programaDisciplina.setAulaSemestre(i);
				programaDisciplina.setPlanoDisciplina(planoDisciplina);
				programaDisciplina.setDataAula(diasAula.get(i));
				
				programaDisciplinaService.save(programaDisciplina);
			}
			
			// programa do plano de disciplina
			ProgramaPlanoDisciplina programaPlanoDisciplina = new ProgramaPlanoDisciplina();
			
			programaPlanoDisciplina.setPlanoDisciplina(planoDisciplina);
			programaPlanoDisciplina.setDisciplina(disciplina);
			
			programaPlanoDisciplinaService.save(programaPlanoDisciplina);
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", "success");
		return SUCCESS;
	}
	
	@Action(value = "buscarDataAula", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
	   		   									 @Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarDataAula() {
		String datas = "";
		String idDisciplina = request.getParameter("idDisciplina");
		
		try {
			// busca Disciplina
			Disciplina disciplina = new Disciplina();
			List<Semestre> semestre = new ArrayList<Semestre>();
			
			semestre = semestreService.findAll();
			if(semestre.size() > 0) {
				disciplina = disciplinaService.findOne(Integer.valueOf(idDisciplina));
				
				// busca dias da materia
				List<Integer> dias = new ArrayList<Integer>();
				
				String[] aux = disciplina.getHorariosAula().split("-");
				
				for(int i = 1; i < aux.length; i++) {
					if((aux[i].substring(0, 1)).equals("d")) {
						// adiciona 1 para que o calendar entenda o dia da semana
						dias.add(Integer.valueOf(aux[i].substring(1, 2)) + 1);
					}
				}
				
				SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");  
				String dataFim = f.format(semestre.get(0).getDataFim());
				String dataInicio = f.format(semestre.get(0).getDataInicio());
				
				// busca quantidade de dias no semestre
//				int quantDias = planoDisciplinaService.findByDiferencaEntreDatas("2014-09-02", "2014-01-01");
				int quantDias = planoDisciplinaService.findByDiferencaEntreDatas(dataFim, dataInicio);
				
				// busca cada data dentro do semestre e verifica se é dia daquela aula
				for(int i = 0; i <= quantDias; i++) {
//					String data = planoDisciplinaService.findByDataDisciplina("2014-01-01", i);
					String data = planoDisciplinaService.findByDataDisciplina(dataInicio, i);
					
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
					Date date = (Date) formatter.parse(data);
					
					Calendar cal = Calendar.getInstance();  
					cal.setTime(date);
					int day = cal.get(Calendar.DAY_OF_WEEK);
					
					// verifica se é o dia da semana que há aula
					for(Integer j : dias) {
						if(day == j.intValue()) {
							String[] dt = data.split("-");
							
							datas = datas + ";" +dt[2]+"/"+dt[1]+"/"+dt[0];
						}
					}
				}
				
				datas = datas.substring(1);
			}
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		
		jsonData.put("success", datas);
		return SUCCESS;
	}
	
	private List<Date> diasDisciplina(Date dt1, Date dt2, Disciplina disciplina) {
		
		// recupera os dias da semana que há aula da disciplina
		List<Integer> diasSemanaDisciplina = new ArrayList<Integer>();
		String[] aux = disciplina.getHorariosAula().split("-");
		
		for(int i = 1; i < aux.length; i++) {
			if((aux[i].substring(0, 1)).equals("d")) {
				diasSemanaDisciplina.add(Integer.valueOf(aux[i].substring(1, 2)) + 1);
			}
		}
		
		// recupera todos os dias dentro do semestre que há aula da disciplina
		List<Date> dias = new ArrayList<Date>();
		
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt1);
        for (Date dt = dt1; dt.compareTo(dt2) <= 0; ) {
        	for(Integer j : diasSemanaDisciplina) {
				if(cal.get(Calendar.DAY_OF_WEEK) == j.intValue()) {
					dias.add(dt);
				}
        	}
            cal.add(Calendar.DATE, +1);
            dt = cal.getTime();
        }
        
        return dias;
	}
	
	public List<Disciplina> getProfessorDisciplinas() {
		return professorDisciplinas;
	}

	public void setProfessorDisciplinas(List<Disciplina> professorDisciplinas) {
		this.professorDisciplinas = professorDisciplinas;
	}

	public List<ProgramaPlanoDisciplina> getProgramaPlanoDisciplinasProfessor() {
		return programaPlanoDisciplinasProfessor;
	}

	public void setProgramaPlanoDisciplinasProfessor(List<ProgramaPlanoDisciplina> programaPlanoDisciplinasProfessor) {
		this.programaPlanoDisciplinasProfessor = programaPlanoDisciplinasProfessor;
	}

}