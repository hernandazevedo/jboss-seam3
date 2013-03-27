package br.com.maven3.tutorial;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.maven3.tutorial.anotations.RepositoryManager;
import br.com.maven3.tutorial.entidades.Carro;
import br.com.maven3.tutorial.entidades.Widget;

public class WidgetListProducer
{
   @Inject @RepositoryManager EntityManager widgetRepository;
   
   @Produces
   @Named
   @RequestScoped
   @SuppressWarnings("unchecked")
   List<Widget> getWidgets() {
      return widgetRepository.createQuery("select w from Widget w order by w.name").getResultList();
   }
   
   @Produces
   @Named
   @RequestScoped
   @SuppressWarnings("unchecked")
   List<Carro> getCarros() {
	   
      return widgetRepository.createQuery("select c from Carro c order by c.nome").getResultList();
   }
}
