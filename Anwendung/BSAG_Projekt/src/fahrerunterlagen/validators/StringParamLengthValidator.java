package fahrerunterlagen.validators;

import java.text.MessageFormat;
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
 * Validator der Zeicheneingaben auf korrekte Anzahl der Zeichen überprüft.
 * Braucht die zusätzlichen Parameter 'min' und 'max', die dem Validator als f:attribute 
 * übergeben werden müssen.
 * Validator reagiert, wenn die Eingabe nicht 'null' ist.
 */
@FacesValidator(value = "stringParamLength")
public class StringParamLengthValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent comp, Object obj) throws ValidatorException {
	
		Map<String,String> params = 
                context.getExternalContext().getRequestParameterMap();
	  //String buttonEinreichen = params.get("fundzettelForm:fundButtonEinreichen");
	  //String buttonEinreichen2 = params.get("verspaetungForm:fundButtonEinreichen");
	  String input = (String) obj;
	  
	 // if(input != null || buttonEinreichen != null || buttonEinreichen2 != null) { 
		  if(input != null ) {
	  
		  
		String minS = (String) comp.getAttributes().get("min");
		String maxS = (String) comp.getAttributes().get("max");
		
		
		System.out.println("StringParamLength input = "+input);
		int size = input.length();
		System.out.println("SPL input length = "+size);
		int min = Integer.parseInt(minS);
		int max = Integer.parseInt(maxS);
//		long min = Long.parseLong(minS);
//		long max = Long.parseLong(maxS);
		String mb = context.getApplication().getMessageBundle();
		ResourceBundle rb = ResourceBundle.getBundle(mb);
		
		if(size < min) {
			System.out.println("SPL size kleiner min="+min);
			Object[] param = new Object[1];
			param[0] = min;
			//context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, form1.format(param), form2.format(param)));
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFormat.format(rb.getString("fuStringLengthMIN"), param), MessageFormat.format(rb.getString("fuStringLengthMIN_detail"), param)));
					
		}
		if( size > max) {
//			String message = rb.getString("fuMAX");
//			String messageDetail = rb.getString("fuMAX_detail");
			//context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, messageDetail));
			Object[] param2 = new Object[1];
			param2[0] = max;
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFormat.format(rb.getString("fuStringLengthMAX"), param2), MessageFormat.format(rb.getString("fuStringLengthMAX"), param2)));
			
					
		}
	  }
	}

}
