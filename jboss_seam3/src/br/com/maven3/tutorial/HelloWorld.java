package br.com.maven3.tutorial;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.maven3.tutorial.bundle.MessageResource;

@Named
@SessionScoped
public class HelloWorld implements Serializable
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String text = "Hello World!";

	private String letters;
   
	private String numbers;
   
    private String email;
   
    private String painel;
   
   
    public HelloWorld() {}
   
   @PostConstruct
   public void initialize()
   {
      System.out.println(this.getClass().getSimpleName() + " was constructed");
   }

   
   public String check(){
	   
	   return "";
   }
   
   public String getText()
   {
      return text;
   }

   @NotNull
   @NotEmpty
   @Pattern(regexp = "[A-Za-z]*", message = "must contain only letters")
   public String getLetters()
   {
      return letters;
   }

   public void setLetters(String letters)
   {
      this.letters = letters;
   }

   @NotNull
   @NotEmpty
   @Digits(fraction = 0, integer = 2)
   public String getNumbers()
   {
      return numbers;
   }

   public void setNumbers(String numbers)
   {
      this.numbers = numbers;
   }

   @NotNull
   @NotEmpty
   @Email
   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }
   
   public String getPainel() {
	return painel;
   }
   public void setPainel(String painel) {
	this.painel = painel;
   }
   public String getMessage() {		
		String key = "someMessage";
		String msg = new MessageResource().getValue(key);
		return MessageFormat.format(msg, "SomeValue");				
	}
}
