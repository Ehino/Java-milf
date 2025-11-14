public class Altushka extends DirtyGirls{

	private int countBoyfriend;

	public Altushka(String name, String city, int age, boolean cooking, int countBoyfriend){
		super(name, city, age, cooking);
		this.countBoyfriend = countBoyfriend;
	}
	
	@Override
	public String getInfo() {
		return "Имя: " + name + 
		"Город: " + city +
		"Возраст: " + age +
		"Умение готовить: " + (cooking? "Да" : "Нет") +
		"Категория: Альтушка" + 
		"Количество парней: " + countBoyfriend; 
	}

	
}