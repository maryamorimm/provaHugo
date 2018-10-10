package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
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
	
	
	private ArrayList<String> selectIdAlunos;
	
	@Inject
	private TurmaServicos service;
	
	@Inject
	private AlunoServicos alunoService;
	
	private Turma turmaSelecionada = new Turma();
	
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
	
	
	public ArrayList<String> getSelectIdAlunos() {
		return selectIdAlunos;
	}

	public void setSelectIdAlunos(ArrayList<String> selectIdAlunos) {
		this.selectIdAlunos = selectIdAlunos;
	}


	//Métdos 
	
	
			//Atualizar Turma
	public void atualizar() {
		turma = new Turma();
		turmas = (Set<Turma>)getService().getAll();
	}
	
	
			//Remover Turma
	public void removerTurma(Turma t) {
		service.remove(t);
		atualizar();
	}
	
	
			//Botão de concluir edição de lista de alunos da turma
	
	public void concluirEdicao() {
		Set<Aluno> alunosTurmaSelecionada = new HashSet<Aluno>();
		alunosTurmaSelecionada.addAll(getPickListAluno().getTarget());
		turmaSelecionada.getAlunos().addAll(alunosTurmaSelecionada);
		service.update(turmaSelecionada);
		atualizar();
		PrimeFaces.current().ajax().update("form");
		setPickListAluno(new DualListModel<Aluno>());
		setRenderPanelEdicao(false);
	}


			//Salvar Turma
	
	public void salvarTurma() {
		List<Aluno> alunos = alunoService.getAll();
		
					//Não selecionou alunos para salvar :
		
		if (selectIdAlunos.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERRO", "Selecione ao menos um aluno!"));
		} else {
			
			//Percorrer a lista de Id e de Alunos para encontrar seu correspondente e salvar:

			Set<Aluno> alunosC = new HashSet<Aluno>();
			
			for (String l : selectIdAlunos) {
				for (Aluno a : alunos) {
					if (Long.toString((a.getId())).equals(l)) {
						alunosC.add(a);
					}
				}
			}
			
			//Verificar a existência de alunos duplicados:
			
			String alunosDuplicados = "Os alunos: ";
			
			boolean ehDuplicado = false;
			for (Aluno a : alunosC) {
				for (Turma t : service.getAll()) {
					for (Aluno b : t.getAlunos()) {
						if (a.getId().equals(b.getId())) {
							ehDuplicado = true;
							alunosDuplicados += a.getNome() + ", ";
						}
					}
				}
			}

			if (ehDuplicado) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("ERRO", alunosDuplicados + "já estao cadastrados!"));
				atualizar();
			} else {
				turma.setAlunos(alunosC);
				service.save(turma);
				atualizar();
			}

		}
		atualizar();

	}
	
			//Iniciar lista de alunos totais e lista de alunos selecionados para a turma:
	public void iniciarDualListAluno() {
		ArrayList<Aluno> alunosSource = new ArrayList<Aluno>();
		ArrayList<Aluno> alunosTarget = new ArrayList<Aluno>();
		alunosSource.addAll(getAlunoService().getAll());
		alunosSource.removeAll(turmaSelecionada.getAlunos());
		alunosTarget.addAll(turmaSelecionada.getAlunos());
		setPickListAluno(new DualListModel<Aluno>(alunosSource, alunosTarget));
	}

	

}
