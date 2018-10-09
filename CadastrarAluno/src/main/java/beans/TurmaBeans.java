package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import Servicos.AlunoServicos;
import Servicos.TurmaServicos;
import entidades.Aluno;
import entidades.Turma;

public class TurmaBeans implements Serializable {
	
	// variáveis
	
	private static final long serialVersionUID = 1L;
	private Turma turma = new Turma();
	private Set<Turma> turmas;
	
	
	public ArrayList<String> getSelectIdAlunos() {
		return selectIdAlunos;
	}

	public void setSelectIdAlunos(ArrayList<String> selectIdAlunos) {
		this.selectIdAlunos = selectIdAlunos;
	}

	private ArrayList<String> selectIdAlunos;
	
	
	@Inject
	private TurmaServicos service;
	
	@Inject
	private AlunoServicos alunoService;
	
	private boolean renderPanelEdicao;
	private DualListModel<Aluno> pickListAluno;


	
	
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
	
	
	public AlunoServicos getAlunoService() {
		return alunoService;
	}

	public void setAlunoService(AlunoServicos alunoService) {
		this.alunoService = alunoService;
	}

	public boolean isRenderPanelEdicao() {
		return renderPanelEdicao;
	}

	public void setRenderPanelEdicao(boolean renderPanelEdicao) {
		this.renderPanelEdicao = renderPanelEdicao;
	}

	public DualListModel<Aluno> getPickListAluno() {
		return pickListAluno;
	}

	public void setPickListAluno(DualListModel<Aluno> pickListAluno) {
		this.pickListAluno = pickListAluno;
	}

	//Métdos 
	
	
			//Limpar Turma
	public void limpar() {
		turma = new Turma();
		turmas = (Set<Turma>)getService().getAll();
	}
	
			//Remover Turma
	public void removerTurma(Turma t) {
		service.remove(t);
		limpar();
	}


	
	

}
