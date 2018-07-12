package fahrerunterlagen.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*
 * Validator für eine gültige Betriebstelleneingabe: b oder B gefolgt von zwei Ziffern
 */
@FacesValidator(value = "BValidator")
public class BetriebsstelleValidator implements Validator{

	private String regex="(b|B)\\d{2}";
	@Override
	public void validate(FacesContext context, UIComponent component, Object obj) throws ValidatorException {
		String pnr = (String)obj;
		//System.out.println("BValidator");
		if(!pnr.matches(regex)) {
			String mb = context.getApplication().getMessageBundle();
			ResourceBundle rb = ResourceBundle.getBundle(mb);
			String message = rb.getString("fuNoBNR");
			String messageDetail = rb.getString("fuNoBNR_detail");
			context.addMessage(component.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, messageDetail));
			
		}
		
	}

}
