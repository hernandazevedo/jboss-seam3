package br.com.maven3.tutorial.handler;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.maven3.tutorial.datamodel.PessoaDataModel;
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
	private List<Pessoa> selection;
	private Long pessoaComboSelected;
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
	private Integer numeroCombo;
  
	private Pessoa editedPessoa;

	@NotEmpty
	@NotNull
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public String index(){
		System.out.println("Chamou index");
		return "index";
	}
	
	public String cadastrarPessoa(){
		pessoaServiceBean.cadastrarPessoa(nome, idade);
		   
    return "sucesso";
    }
	
	
	public void addInfo(ActionEvent actionEvent) { 
		System.out.println("Chamou o addInfo");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sample info message", "PrimeFaces rocks!"));  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sample info message", "PrimeFaces rocks!"));
        
        
    } 
	public String removerPessoa(Long id){
		pessoaServiceBean.removerPessoa(id);
		return "sucesso";
	}
	
	public PessoaDataModel getPessoas() {
		
	      return new PessoaDataModel(pessoaServiceBean.getPessoas());
    }
	
	public List<Pessoa> getPessoasCombo() {
		
	      return pessoaServiceBean.getPessoas();
   }
	
	public List<Pessoa> getSelection() {
		return selection;
	}
	public void setSelection(List<Pessoa> selection) {
		this.selection = selection;
	}

	public String removerSelecionados(){
		
		for(Pessoa p : selection){
			pessoaServiceBean.removerPessoa(p.getId());
		}
		selection.clear();
		
		return "sucesso";
	}
	 
	 
	 public Pessoa getEditedPessoa() {
		return editedPessoa;
	}
	public void setEditedPessoa(Pessoa editedPessoa) {
		this.editedPessoa = editedPessoa;
	}
	
	public Integer getNumeroCombo() {
		return numeroCombo;
	}
	public void setNumeroCombo(Integer numeroCombo) {
		this.numeroCombo = numeroCombo;
	}
	public Long getPessoaComboSelected() {
		return pessoaComboSelected;
	}
	public void setPessoaComboSelected(Long pessoaComboSelected) {
		this.pessoaComboSelected = pessoaComboSelected;
	}
}


