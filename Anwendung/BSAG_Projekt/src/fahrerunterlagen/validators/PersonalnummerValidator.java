package fahrerunterlagen.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*
 * Validator für eine gültige Personalnummereingabe: p oder P gefolgt von vier Ziffern.
 */
@FacesValidator(value = "PNrValidator")
public class PersonalnummerValidator implements Validator{

	private String regex="(p|P)\\d{4}";
	@Override
	public void validate(FacesContext context, UIComponent component, Object obj) throws ValidatorException {
		
		String pnr = (String)obj;
		//System.out.println("Pnr validator für "+pnr);
		if(!pnr.matches(regex)) {
			//System.out.println("no match");
			String mb = context.getApplication().getMessageBundle();
			ResourceBundle rb = ResourceBundle.getBundle(mb);
			String message = rb.getString("fuNoPNR");
			String messageDetail = rb.getString("fuNoPNR_detail");
			context.addMessage(component.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, messageDetail));
			
		}
		
	}

}
