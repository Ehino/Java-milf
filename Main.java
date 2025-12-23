import database.DatabaseHandler;
import models.Altushka;
import models.Milfa;
import models.UserEmployer;
import windows.FirstWindows;
import windows.MainAlt;
import windows.MainEmp;
import windows.MainMilf;

public class Main {
	public static void main(String[] args){
		String role = "Emp", login = "Elnur";

		//String role = "Alt", login = "Alt";

		//String role = "Milfa", login = "Milfa"; 

		DatabaseHandler dbHandler = new DatabaseHandler();
	
		if (role.equals("Alt")){
			Altushka altushka = dbHandler.getInfoAlt(login);            
			new MainAlt(altushka).setVisible(true);
		} else if (role.equals("Milfa")) {
			Milfa milfa = dbHandler.getInfoMilf(login);
			new MainMilf(milfa).setVisible(true);
		} else if(role.equals("Emp")){
			UserEmployer employer = dbHandler.getInfoEmp(login);
			new MainEmp(employer).setVisible(true);
		} else{
			FirstWindows firstWindows = new FirstWindows();
			firstWindows.setVisible(true);
		}
	}
}



