
public class Milfa extends DirtyGirls{

	private int children;
	private int  husband;
	private String detdom;

	public Milfa(String name, String password, String city, int age, boolean cooking,int children, int husband){
		super(name, password, city, age, cooking);
		this.children = children;
		this.husband = husband;
		this.detdom = "Не приемные";
		Proverka(children, husband);
	}
	
	public void Proverka(int children, int husband){
		if(children > 1 && husband == 0){
			detdom = "Приемные";
		} else{
			detdom = "Не приемные";
		}
	}

	@Override
	public String getInfo() {
		return "Имя: " + name + 
		"\nПароль: " + password + 
		"\nГород: " + city +
		"\nВозраст: " + age +
		"\nУмение готовить: " + (cooking? "Умеет" : "Не умеет") +
		"\nКатегория: Милфа" + 
		"\nКоличество мужей: " + husband +
		"\nКоличество детей: " + children +
		"\nПриемные ли дети: " + (detdom);
	}
}