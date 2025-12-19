import database.DatabaseHandler;
import models.UserEmployer;
import windows.MainEmp;

public class Main {
	public static void main(String[] args){
		/* FirstWindows firstWindows = new FirstWindows();
		firstWindows.setVisible(true); */
		


		DatabaseHandler dbHandler = new DatabaseHandler();
        String userRole = dbHandler.ResultUser("Elnur", "12345");

		UserEmployer employer = dbHandler.getInfoEmp("Elnur");            
        new MainEmp(employer).setVisible(true);
	}
}

