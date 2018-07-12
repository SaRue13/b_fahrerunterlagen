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
 * Validator der Zahleingaben auf korrekte Anzahl der Ziffern überprüft.
 * Funktioniert für Eingaben des Typs long (JSF: Verwendung des f:convertNumber tags,
 * braucht die zusätzlichen Parameter 'min' und 'max', die dem Validator als f:attribute 
 * übergeben werden müssen.
 * Validator reagiert, wenn die Eingabe nicht '0' ist.
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
	  String buttonEinreichen2 = params.get("verspaetungForm:fundButtonEinreichen");
	  //Wenn Einreichen = null, keine Validierung
	 // System.out.println("Param Length");
	 // if(buttonEinreichen != null || buttonEinreichen2 != null) {
	  long input = (long) obj;
	  if(input != 0) {
		String minS = (String) comp.getAttributes().get("min");
		String maxS = (String) comp.getAttributes().get("max");
//		System.out.println("ParamLengthValidator: min="+minS);
//		System.out.println("ParamLengthValidator");
		//int input = (int) obj;
		
		//ich will anzahl der ziffern
		long size = (long) Math.log10(input) + 1;
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
		
		if(size < min) {
			/*String message = rb.getString("fuMINValidation");
			String messageDetail = rb.getString("fuMINValidation_detail");
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, messageDetail));*/
			//Nutzen jeweils nur {0} für min/max Wert
//			String messagePattern = rb.getString("fuMINValidation");
//			MessageFormat form1 = new MessageFormat(messagePattern);
//			String messageDetailPattern = rb.getString("fuMINValidation_detail");
//			MessageFormat form2 = new MessageFormat(messageDetailPattern);
//			int[] param = new int[1];
			Object[] param = new Object[1];
			param[0] = min;
			//context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, form1.format(param), form2.format(param)));
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFormat.format(rb.getString("fuMINValidation"), param), MessageFormat.format(rb.getString("fuMINValidation_detail"), param)));
					
		}
		if( size > max) {
//			String message = rb.getString("fuMAX");
//			String messageDetail = rb.getString("fuMAX_detail");
			//context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, messageDetail));
			Object[] param2 = new Object[1];
			param2[0] = max;
			context.addMessage(comp.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFormat.format(rb.getString("fuMAX"), param2), MessageFormat.format(rb.getString("fuMAX_detail"), param2)));
			
					
		}
	  }
	}

}
