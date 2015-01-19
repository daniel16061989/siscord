package br.com.ufu.bsi.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "professores")
public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_professor")
	private Integer idProfessor;
	
	@Column(name = "nome_professor")
	private String nomeProfessor;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "tipo_professor")
	private Character tipoProfessor;
	
	@Transient
	public static final Character TIPO_PROFESSOR_COORDENADOR = 'C';
	@Transient
	public static final Character TIPO_PROFESSOR_NORMAL = 'N';
	@Transient
	public static final Character TIPO_PROFESSOR_COLEGIADO = 'B';
	
	public Integer getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Integer idProfessor) {
		this.idProfessor = idProfessor;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Character getTipoProfessor() {
		return tipoProfessor;
	}

	public void setTipoProfessor(Character tipoProfessor) {
		this.tipoProfessor = tipoProfessor;
	}
	
}
