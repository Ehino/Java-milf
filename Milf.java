
import java.util.Scanner;



public class Milf extends DirtyGirls{

	private boolean children;
	private boolean husband;
	private boolean detdom;

	public Milf(String name, String city, byte old, boolean status, boolean cooking, boolean category, boolean children, boolean husband) {
		super(name, city, old, status, cooking, category);
		Proverka(children,husband);
	}
	
	public void Proverka(boolean children, boolean husband){
		this.children = children;
		this.husband = husband;
		
		Scanner scanner = new Scanner(System.in);
		
		if(children & !husband){
		System.out.print("Ваши дети приемные?(0 - Нет, 1 - Да)");
		boolean detdom = scanner.nextBoolean();
	}
	}
}