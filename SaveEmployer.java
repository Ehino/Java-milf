import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class SaveEmployer{

    public static void save(Employer name){
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
