public abstract class Employer{
    private static int id;

    public static int getId() { return id; }

    protected String name;
    protected String password;


    protected String city;
    protected String companyName;
    protected String jobDescribe;
    protected String girlType;
    protected String requirements;
    protected boolean advertStatus;

    public Employer(int id){
        this.id = id;
    }

    public Employer(String name, String password,String city, String companyName, String jobDescribe,String girlType, String requirements, boolean advertStatus){
        Employer.id ++;
        this.name = name;
        this.password = password;
        this.city = city;
        this.jobDescribe = jobDescribe;
        this.companyName = companyName;
        this.girlType = girlType;
        this.requirements = requirements;
        this.advertStatus = advertStatus;
    }

    public abstract String getInfo();
}