import java.util.Scanner;

public class Milfa extends DirtyGirls{

	private int children;
	private int  husband;
	private boolean detdom = false;

	public Milfa(String name, String city, int age, boolean cooking,int children, int husband, boolean detdom){
		super(name, city, age, cooking);
		this.children = children;
		this.husband = husband;
		this.detdom = detdom;
		Proverka(children, husband);
	}
	
	public void Proverka(int children, int husband){
		Scanner scanner = new Scanner(System.in);
		
		if(children > 1 && husband == 0){
			while(true){
				System.out.print("Ваши дети приемные?(1.Да 2.Нет)");
				int a = scanner.nextInt();
				if(a == 1){
					detdom = true;
					break;
				} else if(a == 2){
					detdom = false;
					break;
				}
				else{
					System.out.println("Неверный выбор. 1 - Да, 2 - Нет");
				}
			}
		}
		scanner.close();
	}

	@Override
	public String getInfo() {
		return "Имя: " + name + 
		"Город: " + city +
		"Возраст: " + age +
		"Умение готовить: " + (cooking? "Умеет" : "Не умеет") +
		"Категория: Милфа" + 
		"Количество мужей: " + husband +
		"Количество детей: " + children +
		"Приемные ли дети: " + (detdom? "Да" : "Нет");
	}
}