package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
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

	private Disciplina disciplinaSelecionada;
	
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
	public void init() {
		atualizar();
		disciplina.getProfessor().setId(0L);
		setRenderPanelCadastro(false);
	}
	
	public void atualizar() {
		disciplina = new Disciplina();
		disciplinas = (Set<Disciplina>) service.getAll();
		discMatriculaAluno = new Disciplina();
		setPickListAluno(new DualListModel<Aluno>(new ArrayList<Aluno>(), new ArrayList<Aluno>()));
	}
	
			// Salvar Disciplina
	
	public void salvarDisc() {
		disciplina.setProfessor(profService.getByID(disciplina.getProfessor().getId()));
		service.save(disciplina);
		atualizar();
	
}
	
			// Remover Disciplina
	
	public void removerDisciplina(Disciplina disciplina) {
		service.remove(disciplina);
		atualizar();
	}
	
	
	
	//Iniciar lista de alunos totais e lista de alunos matriculados na disciplina:

	public void iniciarPickListAluno() {
		ArrayList<Aluno> alunosSource = new ArrayList<Aluno>();
		ArrayList<Aluno> alunosTarget = new ArrayList<Aluno>();
		alunosSource.addAll(getAlunoService().getAll());
		alunosSource.removeAll(discMatriculaAluno.getAlunos());
		alunosTarget.addAll(discMatriculaAluno.getAlunos());
		setPickListAluno(new DualListModel<Aluno>(alunosSource, alunosTarget));
	}

	//salvar lista de alunos totais e lista de alunos matriculados na disciplina:
	
	public void salvarPickListAluno() {
		Set<Aluno> alunosDiscSelecionada = new HashSet<Aluno>();
		alunosDiscSelecionada.addAll(getPickListAluno().getTarget());
		discMatriculaAluno.getAlunos().addAll(alunosDiscSelecionada);
		service.update(discMatriculaAluno);
		atualizar();
		PrimeFaces.current().ajax().update("form");
		setPickListAluno(new DualListModel<Aluno>());
		setRenderPanelCadastro(false);
	}

}
