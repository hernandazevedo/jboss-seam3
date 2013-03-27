package br.com.maven3.tutorial.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.richfaces.component.UIExtendedDataTable;

import br.com.maven3.tutorial.entidades.Pessoa;
import br.com.maven3.tutorial.services.ejb.interfaces.IPessoaServiceLocal;

@Named
@ConversationScoped
public class CadastroPessoaModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
    private Conversation conversation;
	
	public CadastroPessoaModel(){
		
		System.out.println("Chamou o construtor");
	}
	
	@PostConstruct
	public void start(){
		if(conversation.getId() == null)
				conversation.begin();
	}
	
	@EJB(beanInterface=IPessoaServiceLocal.class,beanName="PessoaServiceBean")
	private IPessoaServiceLocal pessoaServiceBean;
	@SuppressWarnings("rawtypes")
	private HashSet selection;
	private Pessoa editedPessoa;
	private List<Pessoa> selectionItems = new ArrayList<Pessoa>();
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
		selection.clear();
		selectionItems.clear();
		return "sucesso";
	}
	
	public String atualizarPessoa(){
		
		pessoaServiceBean.atualizarPessoa(editedPessoa);
		return "sucesso";
	}
	
	public String removerSelecionados(){
		
		for(Pessoa p : selectionItems){
			pessoaServiceBean.removerPessoa(p.getId());
		}
		selection.clear();
		selectionItems.clear();
		return "sucesso";
	}
	
	public List<Pessoa> getPessoas() {
	      return pessoaServiceBean.getPessoas();
    }
	
	@SuppressWarnings("rawtypes")
	public HashSet getSelection() {
		return selection;
	}
	
	@SuppressWarnings("rawtypes")
	public void setSelection(HashSet selection) {
		this.selection = selection;
	}
	
	 public void selectionListener(AjaxBehaviorEvent event) {
        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        selectionItems.clear();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
            	Pessoa selectedPessoa = (Pessoa) dataTable.getRowData();
                selectionItems.add(selectedPessoa);
                editedPessoa = selectedPessoa;
            }
        }
        dataTable.setRowKey(originalKey);
    }
	public Pessoa getEditedPessoa() {
		return editedPessoa;
	}
	public void setEditedPessoa(Pessoa editedPessoa) {
		this.editedPessoa = editedPessoa;
	}
}
