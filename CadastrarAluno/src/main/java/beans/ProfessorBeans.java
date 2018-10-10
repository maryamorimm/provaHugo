package beans;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Servicos.ProfessorServicos;
import entidades.Professor;

public class ProfessorBeans  implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private Professor professor = new Professor();
	private Set<Professor> profs;
	
	@Inject
	private ProfessorServicos service;
	private String confirmarSenha;

	
	
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
	
	@PostConstruct
	private void init() {
		service = new ProfessorServicos();
	}
	
	
				// Atualizar professores
	public void atualizar() {
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
				atualizar();
			}
		}

	}
	
	
				// Remover professores
	public void removerProf(Professor p) {
		service.remove(p);
		atualizar();
	}
	
	

	
	public void onRowEdit (Professor p) {
		service.update(p);
		FacesMessage msg = new FacesMessage("Professor editado", p.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		atualizar();
	}
	
	public String getUserLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return "Olá! Faça o login para usar o sistema";
		}
		return "Olá, "+userPrincipal.getName();
	}

	public void efetuarLogout() throws IOException, ServletException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.invalidate();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		request.logout();
		ec.redirect(ec.getApplicationContextPath());
	}

	public boolean isUserInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}

		


}
