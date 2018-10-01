package beans;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import Servicos.DisciplinaServicos;
import entidades.Disciplina;
import entidades.Professor;

public class DisciplinaBeans implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Disciplina disciplina = new Disciplina();
	private Set<Disciplina> disciplinas;
	
	@Inject
	private DisciplinaServicos service;
	
	@ManagedProperty(value="#{professorBean}")
	private ProfessorBeans profBean;

	
// get e set 
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public DisciplinaServicos getService() {
		return service;
	}

	public void setService(DisciplinaServicos service) {
		this.service = service;
	}

	public ProfessorBeans getProfBean() {
		return profBean;
	}

	public void setProfBean(ProfessorBeans profBean) {
		this.profBean = profBean;
	}

	
	// métodos 
	
	
	@PostConstruct
	private void init() {
		service = new DisciplinaServicos();
	}

	private void salvarDisciplina() {
		for(Professor p: profBean.getProfs()) {
			if(disciplina.getProfessor().getId() == p.getId()) {
				disciplina.setProfessor(p);
			}
		}
		service.save(disciplina);
		disciplina = new Disciplina();
	}

}
