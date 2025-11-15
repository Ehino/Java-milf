import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class SaveDitryGirls{

	public static void save(DirtyGirls name){
		int id = DirtyGirls.getId();
		String file = "DirtyGirls/" + id + ".txt";

		try (PrintWriter writer = new PrintWriter(new FileWriter(file))){
			writer.println(name.getInfo());
		} catch (IOException e) { e.printStackTrace();}
            
	}
}
