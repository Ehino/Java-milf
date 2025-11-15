public class Altushka extends DirtyGirls{

	private int countBoyfriend;

	public Altushka(String name, String city, int age, boolean cooking, int countBoyfriend){
		super(name, city, age, cooking);
		this.countBoyfriend = countBoyfriend;
	}
	
	@Override
	public String getInfo() {
		return "\nИмя: " + name + 
		"\nГород: " + city +
		"\nВозраст: " + age +
		"\nУмение готовить: " + (cooking? "Да" : "Нет") +
		"\nКатегория: Альтушка" + 
		"\nКоличество парней: " + countBoyfriend; 
	}

	
}