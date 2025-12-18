package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import models.DirtyGirls;
import models.Employer;
import models.UserEmployer;


public class SaveUsers{

	public static void saveDitryGirls(DirtyGirls name){
		int id = DirtyGirls.getId();
		String file = "DirtyGirls/" + id + ".txt";

		File dir = new File("DirtyGirls");
        if (!dir.exists()) {
            dir.mkdirs();
        }

		try (PrintWriter writer = new PrintWriter(new FileWriter(file))){
			writer.println(name.getInfo());
		} catch (IOException e) { e.printStackTrace();}
            
	}

	public static void saveEmployer(Employer name){
        int id = UserEmployer.getId();
        String file = "Employer/" + id + ".txt";

        File dir = new File("Employer");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))){
            writer.println(name.getInfo());
        } catch (IOException e) { e.printStackTrace();}

    }

}
