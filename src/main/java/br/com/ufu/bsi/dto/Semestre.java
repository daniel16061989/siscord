package br.com.ufu.bsi.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Iniciar Semestre
 * @author daniel
 */
@Entity
@Table(name = "semestres")
public class Semestre implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_semestre")
	private Integer idSemestre;
	
	private Integer anoSemestre;
	
	private Integer nrSemestre;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private Date dataReajusteMatriculaInicio;
	
	private Date dataReajusteMatriculaFim;
	
	@Transient
	private String DataInicioFormatada;

	@Transient
	private String DataFimFormatada;
	
	// classe com os recessos
	
	public Integer getIdSemestre() {
		return idSemestre;
	}

	public void setIdSemestre(Integer idSemestre) {
		this.idSemestre = idSemestre;
	}

	public Integer getAnoSemestre() {
		return anoSemestre;
	}

	public void setAnoSemestre(Integer anoSemestre) {
		this.anoSemestre = anoSemestre;
	}

	public Integer getNrSemestre() {
		return nrSemestre;
	}

	public void setNrSemestre(Integer nrSemestre) {
		this.nrSemestre = nrSemestre;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public Date getDataReajusteMatriculaInicio() {
		return dataReajusteMatriculaInicio;
	}

	public void setDataReajusteMatriculaInicio(Date dataReajusteMatriculaInicio) {
		this.dataReajusteMatriculaInicio = dataReajusteMatriculaInicio;
	}

	public Date getDataReajusteMatriculaFim() {
		return dataReajusteMatriculaFim;
	}

	public void setDataReajusteMatriculaFim(Date dataReajusteMatriculaFim) {
		this.dataReajusteMatriculaFim = dataReajusteMatriculaFim;
	}

	public String getDataInicioFormatada() {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String novoFormato = formatador.format(dataInicio);  
		
		return novoFormato;
	}
	
	public String getDataFimFormatada() {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String novoFormato = formatador.format(dataFim);  
		
		return novoFormato;
	}

}