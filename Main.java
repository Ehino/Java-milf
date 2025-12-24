import database.DBHandlerAltushka;
import database.DBHandlerEmployer;
import database.DBHandlerMilfa;
import models.Altushka;
import models.Milfa;
import models.UserEmployer;
import windows.WindowsEditEmployer;
import windows.WindowsFirst;
import windows.WindowsMainAlt;
import windows.WindowsMainEmp;
import windows.WindowsMainMilf;

public class Main {
	public static void main(String[] args){
		String role = "Emp", login = "Elnur";
		
		//String role = "EmpEdit", login = "Elnur";

		//String role = "Alt", login = "Alt";

		//String role = "Milfa", login = "Milfa"; 

		DBHandlerMilfa dbHandlerMilfa = new DBHandlerMilfa();
		DBHandlerEmployer dbHandlerEmployer = new DBHandlerEmployer();
		DBHandlerAltushka dbHandlerAltushka = new DBHandlerAltushka();

	
		if (role.equals("Alt")){
			Altushka altushka = dbHandlerAltushka.getInfoAlt(login);            
			new WindowsMainAlt(altushka).setVisible(true);
		} else if (role.equals("Milfa")) {
			Milfa milfa = dbHandlerMilfa.getInfoMilf(login);
			new WindowsMainMilf(milfa).setVisible(true);
		} else if(role.equals("Emp")){
			UserEmployer employer = dbHandlerEmployer.getInfoEmp(login);
			new WindowsMainEmp(employer).setVisible(true);
		} else if (role.equals("EmpEdit")) {
			UserEmployer employer = dbHandlerEmployer.getInfoEmp(login);
			new WindowsEditEmployer(employer).setVisible(true);
		} else{
			WindowsFirst firstWindows = new WindowsFirst();
			firstWindows.setVisible(true);
		}
	}
}



