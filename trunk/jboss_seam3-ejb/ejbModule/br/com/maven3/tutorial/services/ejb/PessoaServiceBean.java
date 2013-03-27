package br.com.maven3.tutorial.services.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.maven3.tutorial.entidades.Pessoa;
import br.com.maven3.tutorial.services.ejb.interfaces.IPessoaServiceLocal;
import br.com.maven3.tutorial.services.ejb.interfaces.IPessoaServiceRemote;

@Stateless
public class PessoaServiceBean implements IPessoaServiceLocal,IPessoaServiceRemote{

	@PersistenceContext(unitName="widgets")
	EntityManager entityManager;
	
	public void cadastrarPessoa(String nome, Integer idade){
		Pessoa p = new Pessoa();
		p.setNome(nome);
		p.setIdade(idade);
		entityManager.persist(p);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> getPessoas() {
		return	entityManager.createQuery("select p from Pessoa p order by p.nome").getResultList(); 
	}

	public void removerPessoa(Long id) {
		Pessoa p = entityManager.find(Pessoa.class, id);
		
		if(p != null) entityManager.remove(p);
		
	}

	public void atualizarPessoa(Pessoa editedPessoa) {
		Pessoa p = entityManager.find(Pessoa.class, editedPessoa.getId());
		
		if(p != null) entityManager.merge(editedPessoa);
		
	}


}
