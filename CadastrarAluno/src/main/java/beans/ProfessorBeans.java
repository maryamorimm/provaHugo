package beans;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import Servicos.ProfessorServicos;
import entidades.Professor;

public class ProfessorBeans  implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private Professor professor = new Professor();
	private Set<Professor> profs;
	
	@Inject
	private ProfessorServicos service;
	private String confirmarSenha;

	@PostConstruct
	private void init() {
		service = new ProfessorServicos();
	}
	
	// get e set dos atributos
	
	public Professor getProfessor() {
		return professor;
	}


	public void setProfessor(Professor professor) {
		this.professor = professor;
	}



	public Set<Professor> getProfs() {
		return profs;
	}


	public void setProfs(Set<Professor> profs) {
		this.profs = profs;
	}


	public ProfessorServicos getService() {
		return service;
	}


	public void setService(ProfessorServicos service) {
		this.service = service;
	}



	public String getConfirmarSenha() {
		return confirmarSenha;
	}


	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

// métodos 
	
	
				// Limpar professores
	public void limpar() {
		professor = new Professor();
		profs = (Set<Professor>) getService().getAll();
		
	}
	
				// Salvar professores
	public void salvarProfessor() {
		if (!professor.getSenha().equals(confirmarSenha)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "senhas diferentes!"));
		} else {
			boolean mesmoLogin = false;
			for (Professor p : profs) {
				if (professor.getEmail().equals(p.getEmail())) {
					mesmoLogin = true;
				}
			}
			if (mesmoLogin) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("ERROR", "login já existe"));
			} else {
				service.save(professor);
				professor = new Professor();
			}
		}

	}
	
	
				// Remover professores
	public void removerProf(Professor p) {
		service.remove(p);
		limpar();
	}

	
	
	
	


}
