package br.com.maven3.tutorial.bundle;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class MessageResource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundle;

	public ResourceBundle getBundle() {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "msgs");
		}
		return bundle;
	}

	public String getValue(String key) {

		String result = null;
		try {
			result = getBundle().getString(key);
		} catch (MissingResourceException e) {
			result = "???" + key + "??? not found";
		}
		return result;
	}

}
