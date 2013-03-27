package br.com.maven3.tutorial.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carro implements Serializable
{
   private Long id;
   private String nome;
   
   
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   // demonstrates a column name override
   @Column(name = "nome")
   public String getNome() {
	return nome;
   }
   
   public void setNome(String nome) {
	this.nome = nome;
   }
   
   /** Default value included to remove warning.  Remove or modify at will.  */  
   private static final long serialVersionUID = 1L;
}