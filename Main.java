import database.DatabaseHandler;
import models.Milfa;
import windows.MainMilf;


public class Main {
	public static void main(String[] args){
		/* FirstWindows firstWindows = new FirstWindows();
		firstWindows.setVisible(true); */
		


		/* DatabaseHandler dbHandler = new DatabaseHandler();
        String userRole = dbHandler.ResultUser("AltD", "Alt1234");

		Altushka altushka = dbHandler.getInfoAlt("AltD");            
        new MainAlt(altushka).setVisible(true);
 */

		DatabaseHandler dbHandler = new DatabaseHandler();
        String userRole = dbHandler.ResultUser("Milf", "1234");

		Milfa milfa = dbHandler.getInfoMilf("Milf");            
        new MainMilf(milfa).setVisible(true); 
	}
}

