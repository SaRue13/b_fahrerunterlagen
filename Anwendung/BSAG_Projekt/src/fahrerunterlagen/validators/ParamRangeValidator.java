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
 * custom lengthValidator: Bekommt als Parameter mit was min und max ist und
 * reagiert nicht, wenn Speichern gedrückt wird
 */
@FacesValidator(value = "paramRange")
public class ParamRangeValidator implements Validator{
	
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
		//int input = (int) obj;
		long input = (long) obj;
//		int min = Integer.parseInt(minS);
//		int max = Integer.parseInt(maxS);
		long min = Long.parseLong(minS);
		long max = Long.parseLong(maxS);
		String mb = context.getApplication().getMessageBundle();
		ResourceBundle rb = ResourceBundle.getBundle(mb);
		/*
		 * MessageFormat nutzen! 
		 * Eine Message mit Platzhaltern {0} vorbereiten und dann diese.formate(paramArry)
		 * dazu die nötigen Parameter in ein Array packen
		 * int fileCount = 1273;
			 String diskName = "MyDisk";
			 Object[] testArgs = {new Long(fileCount), diskName};
			
			 MessageFormat form = new MessageFormat(
			     "The disk \"{1}\" contains {0} file(s).");
			
			 System.out.println(form.format(testArgs));
			 
		 */
		
		if(input < min || input > max) {
			//System.out.println("praramRange: Wert passt nicht: "+input);
			/*String message = rb.getString("fuMINValidation");
			String messageDetail = rb.getString("fuMINValidation_detail");
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, messageDetail));*/
			//Nutzen jeweils nur {0} für min/max Wert
//			int[] param = new int[1];
			Object[] param = new Object[2];
			param[0] = minS;
			param[1] = maxS;
			//context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, form1.format(param), form2.format(param)));
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFormat.format(rb.getString("fuRange"), param), MessageFormat.format(rb.getString("fuRange_detail"), param)));
					
		}
	  }
	}

}
