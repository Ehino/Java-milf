package models;

public class Altushka extends DirtyGirls{

	private int countBoyfriend;

	public Altushka(String name, String password, String city, int age, boolean cooking, int countBoyfriend){
		super(name, password, city, age, cooking);
		this.countBoyfriend = countBoyfriend;
		this.girlType = "Альтушка";
	}

	public int getCBoyFriend() {
		return countBoyfriend;
	}
	
	@Override
	public String getInfo() {
		return "Имя: " + name + 
		"\nПароль: " + password + 
		"\nГород: " + city +
		"\nВозраст: " + age +
		"\nУмение готовить: " + (cooking? "Умеет" : "Не умеет") +
		"\nКатегория: Альтушка" + 
		"\nКоличество парней: " + countBoyfriend; 
	}

	
}