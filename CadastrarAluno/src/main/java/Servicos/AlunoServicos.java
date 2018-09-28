package Servicos;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

import dao.AlunoDAO;
import entidades.Aluno;
import entidades.Identificavel;
import util.TransacionalCdi;

@ApplicationScoped
public class AlunoServicos implements Serializable, Service<Aluno> {

	private static final long serialVersionUID = -7803325791425670859L;

	@Inject
	private AlunoDAO alunoDAO;

	@Override
	@TransacionalCdi
	public void save(Aluno aluno) {
		alunoDAO.save(aluno);
	}

	@Override
	@TransacionalCdi
	public void update(Aluno aluno) {
		alunoDAO.update(aluno);
	}

	@Override
	@TransacionalCdi
	public void remove(Aluno aluno) {
		alunoDAO.remove(aluno);
	}

	
	@Override
	public Aluno getByID(long alunoId) {
		return (Aluno) alunoDAO.getByID(alunoId);
	}

	@Override
	public List<Aluno> getAll() {
		return alunoDAO.getAll();
	}

}
