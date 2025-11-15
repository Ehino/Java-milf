public abstract class DirtyGirls {
	private static int id;

    public static int getId() {
        return id;
    }

	protected String name, city;
	protected int age;
	protected boolean cooking;

	public DirtyGirls(int id){
		this.id = id;
	}

	public DirtyGirls(String name, String city, int age, boolean cooking){
		DirtyGirls.id++;
		this.name = name;
		this.city = city;
		this.age = age;
		this.cooking = cooking;
	}

	public abstract String getInfo();

}

