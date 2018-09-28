package Servicos;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;

import dao.ProfessorDAO;
import entidades.Aluno;
import entidades.Professor;
import util.TransacionalCdi;

public class ProfessorServicos implements Serializable, Service<Professor> {

	
	private static final long serialVersionUID = -7803325791425670859L;

	@Inject
	private ProfessorDAO professorDAO;

	@Override
	@TransacionalCdi
	public void save(Professor professor) {
		professor.setSenha(hash(professor.getSenha()));
		professorDAO.save(professor);
	}

	@Override
	@TransacionalCdi
	public void update(Professor professor) {
		professorDAO.update(professor);
	}

	@Override
	@TransacionalCdi
	public void remove(Professor professor) {
		professorDAO.remove(professor);
	}

	
	@Override
	public Professor getByID(long professorId) {
		return (Professor) professorDAO.getByID(professorId);
	}

	@Override
	public List<Professor> getAll() {
		return professorDAO.getAll();
	}

	
	private String hash(String password) {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			String output = Base64.getEncoder().encodeToString(digest);
			return output;
		} catch (Exception e) {
			return password;
		}
	}
	
}

	
