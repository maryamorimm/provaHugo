package Servicos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dao.DisciplinaDAO;
import entidades.Disciplina;
import util.TransacionalCdi;

public class DisciplinaServicos  implements Serializable, Service<Disciplina> {
	
	
	
	private static final long serialVersionUID = -7803325791425670859L;

	@Inject
	private DisciplinaDAO disciplinaDAO;

	@Override
	@TransacionalCdi
	public void save(Disciplina disciplina) {
		disciplinaDAO.save(disciplina);
	}

	@Override
	@TransacionalCdi
	public void update(Disciplina disciplina) {
		disciplinaDAO.update(disciplina);
	}

	@Override
	@TransacionalCdi
	public void remove(Disciplina disciplina) {
		disciplinaDAO.remove(disciplina);
	}

	
	@Override
	public Disciplina getByID(long disciplinaId) {
		return (Disciplina) disciplinaDAO.getByID(disciplinaId);
	}

	@Override
	public List<Disciplina> getAll() {
		return disciplinaDAO.getAll();
	}

}
