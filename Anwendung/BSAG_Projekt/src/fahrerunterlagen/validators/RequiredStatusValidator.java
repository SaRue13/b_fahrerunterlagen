package fahrerunterlagen.validators;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import net.bootsfaces.component.selectOneMenu.SelectOneMenu;


/*
 * Status von Button abgreifen und anhand dessen validieren oder nicht
 * Fkt: Wenn speichern, dann nicht required, wenn einreichen, dann schon
 */

@FacesValidator(value = "requiredStatus")
public class RequiredStatusValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent comp, Object obj) throws ValidatorException {
		//Status aus binding aus selectOneMenu holen
		SelectOneMenu statusItem = (SelectOneMenu)comp.getAttributes().get("status");
		String status = (String)statusItem.getValue();
		//System.out.println("Req-validator: status="+status);
		//System.out.println("Req-Val: obj="+obj.getClass().getName());
		//Testen ob Komponente leer:selctoneMenu, Inputtext, date-time-picker
		//::inputTExt: obj ist string, selectOne:obj geht zu string, dtp objekt: Sat Jun 02 12:40:00 CEST 2018
		//System.out.println("objekt: "+obj);
		if(status.equals("einreichen")) {
			switch (obj.getClass().getName()) {
			case "java.lang.Integer": ; break;
			case "java.lang.String": ; break;
			case "java.util.Date" : ; break;
			}
			/*String object = (String)obj;
			if(object.length() == 0 || object == null) {
				System.out.println("Req-Val: IS NULL");
			}*/
		}
	}
	

}
