package br.com.maven3.tutorial.producers;

import javax.ejb.Stateless;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.maven3.tutorial.anotations.RepositoryManager;

@Stateless
public class RepositoryProducer
{
   // NOTE cannot use producer field because Weld attempts to close EntityManager
   @PersistenceContext EntityManager em;

   public @Produces @RepositoryManager EntityManager retrieveEntityManager() {
      return em;
   }

   public void disposeEntityManager(@Disposes @RepositoryManager EntityManager em) {}
}
