
import java.util.Scanner;


public class DirtyGirls {
	private static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        DirtyGirls.id = id;
    }
	private String name, city, description;
	private byte old;
	private boolean status, cooking, category;

	public DirtyGirls(String name, String city, byte old, boolean status, boolean cooking, boolean category){
		DirtyGirls.id++;
		this.name = name;
		this.city = city;
		this.old = old;
		this.status = true;
		this.cooking = cooking;
		this.category = category;
		System.out.println(info());
	}

	@SuppressWarnings("unused")
	private static void addDirtyGirls() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.print("Выберите свою категорию (0 - Милф, 1 - Альтушка): ");
		boolean category = scanner.nextBoolean();

		System.out.print("Ваше имя или никнейм: ");
		String name = scanner.nextLine();
		
		System.out.print("Ваш город: ");
		String city = scanner.nextLine();

		System.out.print("Вы умеете готовить? (0 - Нет, 1 - Да): ");
		boolean cooking = scanner.nextBoolean();

		
	}

	public String info(){
		return "Имя: " + this.name + "\n" + 
				"Город: " + this.city + "\n" + 
				"Возраст: " + this.old + "\n" + 
				"Статус: " + this.status + "\n" + 
				"Умение готовить: " + this.cooking;
	}

}

