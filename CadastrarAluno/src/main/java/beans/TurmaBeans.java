package beans;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import Servicos.TurmaServicos;
import entidades.Turma;

public class TurmaBeans implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Turma turma = new Turma();
	private Set<Turma> turmas;
	
	
	
	@PostConstruct
	private void init() {

	}

	
	@Inject
	private TurmaServicos service;


	
	
	//get e set 
	
	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	public Set<Turma> getTurmas() {
		return turmas;
	}


	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}


	public TurmaServicos getService() {
		return service;
	}


	public void setService(TurmaServicos service) {
		this.service = service;
	}
	
	
	
	//Métdos 
	public void salvarTurma() {

	}
	
	

	
	

}
