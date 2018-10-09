package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import Servicos.AlunoServicos;
import Servicos.DisciplinaServicos;
import Servicos.ProfessorServicos;
import entidades.Aluno;
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

	
// Conferir
	
	@Inject
	private ProfessorServicos profService;
	
	
	@Inject
	private AlunoServicos alunoService;
	
	private Disciplina discMatriculaAluno;
	private boolean renderPanelCadastro;
	private DualListModel<Aluno> pickListAluno;

	
	
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
	
	
	public ProfessorServicos getProfService() {
		return profService;
	}

	public void setProfService(ProfessorServicos profService) {
		this.profService = profService;
	}

	public AlunoServicos getAlunoService() {
		return alunoService;
	}

	public void setAlunoService(AlunoServicos alunoService) {
		this.alunoService = alunoService;
	}

	public Disciplina getDiscMatriculaAluno() {
		return discMatriculaAluno;
	}

	public void setDiscMatriculaAluno(Disciplina discMatriculaAluno) {
		this.discMatriculaAluno = discMatriculaAluno;
	}

	public boolean isRenderPanelCadastro() {
		return renderPanelCadastro;
	}

	public void setRenderPanelCadastro(boolean renderPanelCadastro) {
		this.renderPanelCadastro = renderPanelCadastro;
	}

	public DualListModel<Aluno> getPickListAluno() {
		return pickListAluno;
	}

	public void setPickListAluno(DualListModel<Aluno> pickListAluno) {
		this.pickListAluno = pickListAluno;
	}
	

	
	// métodos 
	

	@PostConstruct
	private void init() {
		service = new DisciplinaServicos();
	}

	
	public void limpar() {
		disciplina = new Disciplina();
		disciplinas = (Set<Disciplina>) service.getAll();
		discMatriculaAluno = new Disciplina();
		setPickListAluno(new DualListModel<Aluno>(new ArrayList<Aluno>(), new ArrayList<Aluno>()));
	}
	
			// Salvar Disciplina
	
	public void salvarDisc() {
		disciplina.setProfessor(profService.getByID(disciplina.getProfessor().getId()));
		service.save(disciplina);
		limpar();
	
}
	
			// Remover Disciplina
	
	public void removerDisciplina(Disciplina disciplina) {
		service.remove(disciplina);
		limpar();
	}
	

	
	// FALTA O RESTO DOS MÉTODOS


}
