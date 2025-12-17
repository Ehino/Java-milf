import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private static final Map<String, DirtyGirls> girlsStorage = new HashMap<>();
    private static final Map<String, Employer> employersStorage = new HashMap<>();
    public static void addGirl(DirtyGirls girl) {
        girlsStorage.put(girl.getName(), girl);
    }
    public static void addEmployer(Employer employer) {
        employersStorage.put(employer.getName(), employer);
    }

    public static Object login(String login, String password) {
        
        if (girlsStorage.containsKey(login)) {
            DirtyGirls girl = girlsStorage.get(login);
            if (girl.getPassword().equals(password)) {
                return girl;
            }
        }

        if (employersStorage.containsKey(login)) {
            Employer employer = employersStorage.get(login);
            if (employer.getPassword().equals(password)) {
                return employer;
            }
        }
        
        return null;
    }
}