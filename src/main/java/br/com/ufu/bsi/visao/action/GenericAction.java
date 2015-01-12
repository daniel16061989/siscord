package br.com.ufu.bsi.visao.action;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.MessageSource;

import br.com.ufu.bsi.service.AlunoService;
import br.com.ufu.bsi.service.DisciplinaService;
import br.com.ufu.bsi.service.GradeHorariaService;
import br.com.ufu.bsi.service.InformacaoLoginService;
import br.com.ufu.bsi.service.MensagemService;
import br.com.ufu.bsi.service.PlanoDisciplinaService;
import br.com.ufu.bsi.service.ProfessorService;
import br.com.ufu.bsi.service.ProfessorTurmaService;
import br.com.ufu.bsi.service.ProgramaDisciplinaService;
import br.com.ufu.bsi.service.ProgramaPlanoDisciplinaService;
import br.com.ufu.bsi.service.ReposicaoAulaService;
import br.com.ufu.bsi.service.SemestreService;
import br.com.ufu.bsi.service.TurmaService;
import br.com.ufu.bsi.service.UsuarioService;
import br.com.ufu.bsi.session.SessionConcierge;
import br.com.ufu.bsi.session.UsuarioLogado;

import com.opensymphony.xwork2.ActionSupport;

@Configurable(autowire = Autowire.BY_NAME, preConstruction = true)
public abstract class GenericAction extends ActionSupport implements Serializable, ServletRequestAware {

	 //Spring
	
	private static final long serialVersionUID = -8098389201002533852L;

	protected transient UsuarioLogado usuarioLogado;
	
	protected transient SessionConcierge sessionConcierge;
	
	protected transient AlunoService alunoService;
	
	protected transient DisciplinaService disciplinaService;
	
	protected transient InformacaoLoginService informacaoLoginService;
	
	protected transient GradeHorariaService gradeHorariaService;
	
	protected transient MensagemService mensagemService;
	
	protected transient PlanoDisciplinaService planoDisciplinaService;
	
	protected transient ProfessorTurmaService professorTurmaService;
	
	protected transient ProfessorService professorService;
	
	protected transient ProgramaDisciplinaService programaDisciplinaService;
	
	protected transient ProgramaPlanoDisciplinaService programaPlanoDisciplinaService;
	
	protected transient ReposicaoAulaService reposicaoAulaService;
	
	protected transient SemestreService semestreService;
	
	protected transient UsuarioService usuarioService;
	
	protected transient TurmaService turmaService;
	
	/**
	 * Este método deve ser invocado apenas pelo Spring para configurar o objeto
	 * 'usuarioLogado'
	 */
	public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * Retorna dados da sessão do usuário logado.
	 */
	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}
	
	/**
	 * Este método deve ser invocado apenas pelo Spring para configurar o objeto 'sessionConcierge'
	 */
	public void setSessionConcierge(SessionConcierge sessionConcierge) {
		this.sessionConcierge = sessionConcierge;
	}
	
	public void setAlunoService(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	public void setDisciplinaService(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}

	public void setInformacaoLoginService(InformacaoLoginService informacaoLoginService) {
		this.informacaoLoginService = informacaoLoginService;
	}

	public void setMensagemService(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}
	
	public void setPlanoDisciplinaService(PlanoDisciplinaService planoDisciplinaService) {
		this.planoDisciplinaService = planoDisciplinaService;
	}

	public void setProfessorTurmaService(ProfessorTurmaService professorTurmaService) {
		this.professorTurmaService = professorTurmaService;
	}

	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	public void setProgramaDisciplinaService(ProgramaDisciplinaService programaDisciplinaService) {
		this.programaDisciplinaService = programaDisciplinaService;
	}
	
	public void setProgramaPlanoDisciplinaService(ProgramaPlanoDisciplinaService programaPlanoDisciplinaService) {
		this.programaPlanoDisciplinaService = programaPlanoDisciplinaService;
	}

	public void setGradeHorariaService(GradeHorariaService gradeHorariaService) {
		this.gradeHorariaService = gradeHorariaService;
	}

	public void setReposicaoAulaService(ReposicaoAulaService reposicaoAulaService) {
		this.reposicaoAulaService = reposicaoAulaService;
	}
	
	public void setSemestreService(SemestreService semestreService) {
		this.semestreService = semestreService;
	}

	public void setTurmaService(TurmaService turmaService) {
		this.turmaService = turmaService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/**
	 * Struts
	 */
	
	public static final String MESSAGE = "message";
	
	public static final String MENSAGEM_ERRO_DEFAULT = "Erro de execução do sistema.";
	
	@Autowired
	protected MessageSource messageSource;

	protected HttpServletRequest request;
	
	protected Map<String, Object> jsonData = new LinkedHashMap<String, Object>();
	protected String menuTree;
	protected String titulo = "";

	protected void addJsonActionSuccess(String key) {
		String message = getText(key);
		if (message.equals("Default")) {
			message = key;
		}
		jsonData.put("success", message);
	}

	protected void addJsonActionMessage(String key) {
		String message = getText(key);
		if (message.equals("Default")) {
			message = key;
		}
		jsonData.put("message", message);
	}

	protected void addJsonActionWarning(String key) {
		String message = getText(key);
		if (message.equals("Default")) {
			message = key;
		}
		jsonData.put("warning", message);
	}

	protected void addJsonActionError(String key) {
		String message = getText(key);
		if (message.equals("Default")) {
			message = key;
		}
		jsonData.put("error", message);
	}

	public String getText(String key) {
		return messageSource.getMessage(key, null, "Default", getLocale());
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}

	public boolean isAjax() {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
