package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class Aluno implements Identificavel {
	
	private String nome;
	@Id
	@GeneratedValue(generator="aluno_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="aluno_seq")
	private Long id;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
