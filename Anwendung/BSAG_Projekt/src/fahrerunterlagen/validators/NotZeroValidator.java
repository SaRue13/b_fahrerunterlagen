package fahrerunterlagen.validators;

import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "notZero")
public class NotZeroValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent comp, Object obj) throws ValidatorException {
		long input = (long)obj;
		//int input2 = (int) obj;
		
		Map<String,String> params = 
                context.getExternalContext().getRequestParameterMap();
	  String buttonEinreichen = params.get("fundzettelForm:fundButtonEinreichen");//fundzettelForm:fundButtonSpeichern
		//System.out.println("NotZERO: "+buttonEinreichen);
	  String buttonEinreichenV = params.get("verspaetungForm:fundButtonEinreichen");
		
		//Auf länge 4 Testen!
		
		if((buttonEinreichen != null || buttonEinreichenV != null) && input == 0) {
			String mb = context.getApplication().getMessageBundle();
			ResourceBundle rb = ResourceBundle.getBundle(mb);
			String message = rb.getString("fuZERO");
			String messageDetail = rb.getString("fuZERO_detail");
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, messageDetail));
					
		}
		
	}

}
