package br.com.maven3.tutorial.services.ejb.interfaces;

import java.util.List;

import br.com.maven3.tutorial.entidades.Pessoa;

public interface IPessoaService {
	void cadastrarPessoa(String nome, Integer idade);
	List<Pessoa> getPessoas();
	void removerPessoa(Long id);
	void atualizarPessoa(Pessoa editedPessoa) ;
}
