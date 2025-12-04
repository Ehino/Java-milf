public abstract class Employer{
    private static int id;

    public static int getId() { return id; }

    protected String password;

    protected String companyName;
    protected String jobDescribe;
    protected String girlType;
    protected String requirements;
    protected boolean advertStatus;

    public Employer(int id){
        this.id = id;
    }

    public Employer(String password, String companyName, String jobDescribe,String girlType, String requirements, boolean advertStatus){
        Employer.id ++;
        this.password = password;
        this.jobDescribe = jobDescribe;
        this.companyName = companyName;
        this.girlType = girlType;
        this.requirements = requirements;
        this.advertStatus = advertStatus;
    }

    public abstract String getInfo();
}