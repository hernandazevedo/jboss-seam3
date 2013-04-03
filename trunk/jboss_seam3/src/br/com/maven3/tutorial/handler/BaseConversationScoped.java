package br.com.maven3.tutorial.handler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;



@ConversationScoped
public abstract class BaseConversationScoped {
	
	private Logger log = Logger.getLogger(BaseConversationScoped.class);
	
	@Inject
	private Conversation conversation;
	
	@PostConstruct
	protected void start(){
		
		if (conversation.isTransient()) {
            conversation.begin();
            log.info("Start - conversation.getId(): " + conversation.getId());
        }
	}
	
	@PreDestroy
	protected void destroy(){
		
		if(!conversation.isTransient()){
			conversation.end();
			log.info("Conversation Id: "+ conversation.getId() + " Ended");
		}
	}
	
}
