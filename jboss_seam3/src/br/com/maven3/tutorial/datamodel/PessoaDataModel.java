package br.com.maven3.tutorial.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.maven3.tutorial.entidades.Pessoa;

public class PessoaDataModel extends ListDataModel<Pessoa> implements SelectableDataModel<Pessoa>{

	public PessoaDataModel() {  
    }  
  
    public PessoaDataModel(List<Pessoa> data) {  
        super(data);  
    }  
  
    @Override  
    public Pessoa getRowData(String rowKey) {  
  
        @SuppressWarnings("unchecked")
		List<Pessoa> list = (List<Pessoa>) getWrappedData();  
  
        for (Pessoa obj : list) {  
            if (obj.getId().equals(Long.valueOf(rowKey))) {  
                return obj;  
            }  
        }  
  
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Pessoa obj) {  
        return obj.getId();  
    }  


   

}
