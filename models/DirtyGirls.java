package models;

public abstract class DirtyGirls {
	private static int id;
	private static int lastId;
	protected  String password;
	protected String name, city, girlType;
	protected int age;
	protected boolean cooking;

	public DirtyGirls(String name, String password,String city, int age, boolean cooking){
		this.id = lastId++;
		this.name = name;
		this.password = password;
		this.city = city;
		this.age = age;
		this.cooking = cooking;
	}

	public abstract String getInfo();

	public static int getId() {
        return id;
    }

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public boolean isCooking() {
        return cooking;
    }

}

