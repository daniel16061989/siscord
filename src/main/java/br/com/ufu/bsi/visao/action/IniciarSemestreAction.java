package br.com.ufu.bsi.visao.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.RecessoSemestre;
import br.com.ufu.bsi.dto.Semestre;

@ParentPackage("default")
@InterceptorRef("coordenacao")
@Namespace(value="/iniciarSemestre")
public class IniciarSemestreAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private Semestre semestreAtual;
	private List<RecessoSemestre> recessosSemestre;
	
	@Action(value = "index", results = {@Result(name = "success", location = "/coordenacao/iniciarSemestre.jsp"),
										@Result(name = "e", location = "/coordenacao/iniciarSemestre.jsp")})
	public String index() {
		try {
			List<Semestre> ss = new ArrayList<Semestre>();
			semestreAtual = new Semestre();
			recessosSemestre = new ArrayList<RecessoSemestre>();
			
			ss = semestreService.findByData();
			
			if(ss.size() > 0) {
				semestreAtual = ss.get(0);
				request.setAttribute("semestreAtual", semestreAtual);
			} else {
				request.setAttribute("semestreAtual", null);
				return "e";
			}
			if(semestreAtual != null)
				if(semestreAtual.getIdSemestre() != null)
					recessosSemestre = recessoSemestreService.findBySemestre(semestreAtual);

		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	@Action(value = "salvarDados", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			  								  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String salvarDados() {
		String ano = request.getParameter("ano");
		String semestre = request.getParameter("semestre");
		String dataInicio = request.getParameter("dataInicio");
		String dataFim = request.getParameter("dataFim");
		String iReajuste = request.getParameter("iReajuste");
		String fReajuste = request.getParameter("fReajuste");
		
		Semestre s = new Semestre();
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		Date dateI;
		Date dateF;
		Date dateRI;
		Date dateRF;
		try {
			dateI = (Date) formatter.parse(dataInicio);
			dateF = (Date) formatter.parse(dataFim);
			dateRI = (Date) formatter.parse(iReajuste);
			dateRF = (Date) formatter.parse(fReajuste);
		
			s.setAnoSemestre(Integer.valueOf(ano));
			s.setNrSemestre(Integer.valueOf(semestre));
			s.setDataInicio(dateI);
			s.setDataFim(dateF);
			s.setDataReajusteMatriculaInicio(dateRI);
			s.setDataReajusteMatriculaFim(dateRF);
		
			semestreService.save(s);
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		jsonData.put("success", "success");
		return SUCCESS;
	}
	
	// TODO verificar se a data do recesso esta dentro do intervalo de tempo do semestre
	@Action(value = "salvarRecesso", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			  									@Result(name="error", type="json", params = {"root","jsonData"})})
	public String salvarRecesso() {
		String dataRecesso = request.getParameter("dataRecesso");
		
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			Date dataR = (Date) formatter.parse(dataRecesso);
			
			List<Semestre> s = new ArrayList<Semestre>();
			s = semestreService.findByData();
			
			RecessoSemestre r = new RecessoSemestre();
			
			r.setDataRecesso(dataR);
			r.setSemestre(s.get(0));
			
			recessoSemestreService.save(r);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", "success");
		return SUCCESS;
	}

	public Semestre getSemestreAtual() {
		return semestreAtual;
	}

	public void setSemestreAtual(Semestre semestreAtual) {
		this.semestreAtual = semestreAtual;
	}

	public List<RecessoSemestre> getRecessosSemestre() {
		return recessosSemestre;
	}

	public void setRecessosSemestre(List<RecessoSemestre> recessosSemestre) {
		this.recessosSemestre = recessosSemestre;
	}
	
}