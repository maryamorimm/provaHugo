package entidades;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Disciplina implements Identificavel {

	private String nome;
	@ManyToOne
	@JoinColumn(name = "id_prof_disc")
	private Professor professor;
	@Id
	@GeneratedValue(generator = "disciplina_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "disciplina_seq")
	private Long id;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "matricula_aluno_disciplina", joinColumns = {
			@JoinColumn(name = "id_disciplina") }, inverseJoinColumns = { @JoinColumn(name = "id_aluno") })
	private Set<Aluno> alunos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

}