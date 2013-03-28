package br.com.maven3.tutorial.model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.maven3.tutorial.entidades.Pessoa;
import br.com.maven3.tutorial.services.ejb.interfaces.IPessoaServiceLocal;

@Named
@ConversationScoped
public class CadastroPessoaHandler implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
    private Conversation conversation;
	
	public CadastroPessoaHandler(){
		
		System.out.println("Chamou o construtor");
	}
	
	@PostConstruct
	public void start(){
		if(conversation.getId() == null)
				conversation.begin();
	}
	
	@EJB(beanInterface=IPessoaServiceLocal.class,beanName="PessoaServiceBean")
	private IPessoaServiceLocal pessoaServiceBean;
	
	private String nome;
	private Integer idade;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public String cadastrarPessoa(){
		pessoaServiceBean.cadastrarPessoa(nome, idade);
		   
    return "sucesso";
    }
	
	public String removerPessoa(Long id){
		pessoaServiceBean.removerPessoa(id);
		return "sucesso";
	}
	
	public List<Pessoa> getPessoas() {
	      return pessoaServiceBean.getPessoas();
    }
	
	
}
