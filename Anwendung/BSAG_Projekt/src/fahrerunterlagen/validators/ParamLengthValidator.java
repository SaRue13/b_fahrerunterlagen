package fahrerunterlagen.validators;

import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/*
 * custom lengthValidator: Bekommt als Parameter mit was min und max ist und
 * reagiert nicht, wenn Speichern gedrückt wird
 */
@FacesValidator(value = "paramLength")
public class ParamLengthValidator implements Validator{
	
	//1: min und max parameter verwerten
	//2. (vor erstens...) checken ob einreichenButton-id = null

	@Override
	public void validate(FacesContext context, UIComponent comp, Object obj) throws ValidatorException {
	
		Map<String,String> params = 
                context.getExternalContext().getRequestParameterMap();
	  String buttonEinreichen = params.get("fundzettelForm:fundButtonEinreichen");
	  //Wenn Einreichen = null, keine Validierung
	  if(buttonEinreichen != null) {
		
		String minS = (String) comp.getAttributes().get("min");
		String maxS = (String) comp.getAttributes().get("max");
//		System.out.println("ParamLengthValidator: min="+minS);
//		System.out.println("ParamLengthValidator");
		int input = (int) obj;
		int min = Integer.parseInt(minS);
		int max = Integer.parseInt(maxS);
		String mb = context.getApplication().getMessageBundle();
		ResourceBundle rb = ResourceBundle.getBundle(mb);
		if(input < min) {
			String message = rb.getString("fuMINValidation");
			String messageDetail = rb.getString("fuMINValidation_detail");
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, messageDetail));
					
		}
		if( input > max) {
			String message = rb.getString("fuMAX");
			String messageDetail = rb.getString("fuMAX_detail");
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, messageDetail));
					
		}
	  }
	}

}
