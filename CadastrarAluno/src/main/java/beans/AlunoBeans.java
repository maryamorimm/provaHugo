package beans;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import Servicos.AlunoServicos;
import entidades.Aluno;


//criar, remover, atuzlizar e buscar 	


public class AlunoBeans implements Serializable {

	

	private static final long serialVersionUID = 1L;


	private Aluno aluno = new Aluno ();
	private Set<Aluno> alunos ;
	@Inject
	private AlunoServicos service;
	
	
	@PostConstruct
	private void init() {
		setAlunos((Set<Aluno>) service.getAll());
		setService(new AlunoServicos());
	}
	
	public void salvarAluno() {
		service.save(aluno);
		limpar();


	}
		

	// Conferir 
	
	
	public void onRowEdit(Aluno obj) {
		service.update(obj);
		FacesMessage msg = new FacesMessage("Aluno editado", obj.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		limpar();
	}

	private void limpar() {
		aluno = new Aluno();
		setAlunos((Set<Aluno>) service.getAll());

	}

	public void removerAluno(Aluno a) {
		service.remove(a);
		limpar();
	}


	
	// get e set 
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Set<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}
	public AlunoServicos getService() {
		return service;
	}
	public void setService(AlunoServicos service) {
		this.service = service;
	}
	
	

}
