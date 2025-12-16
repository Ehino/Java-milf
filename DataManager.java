import java.util.HashMap;
import java.util.Map;

public class DataManager {

    // Хранилище девушек (Милфы и Альтушки)
    private static final Map<String, DirtyGirls> girlsStorage = new HashMap<>();
    
    // Хранилище работодателей
    private static final Map<String, Employer> employersStorage = new HashMap<>();

    // Добавляем девушку в базу
    public static void addGirl(DirtyGirls girl) {
        girlsStorage.put(girl.getName(), girl);
    }

    // Добавляем работодателя в базу
    public static void addEmployer(Employer employer) {
        employersStorage.put(employer.getName(), employer);
    }

    // Универсальный метод входа
    // Возвращает Object, так как это может быть или DirtyGirls, или Employer
    public static Object login(String login, String password) {
        
        // 1. Сначала ищем среди девушек
        if (girlsStorage.containsKey(login)) {
            DirtyGirls girl = girlsStorage.get(login);
            if (girl.getPassword().equals(password)) {
                return girl; // Нашли девушку, возвращаем объект
            }
        }

        // 2. Если не нашли, ищем среди работодателей
        if (employersStorage.containsKey(login)) {
            Employer employer = employersStorage.get(login);
            if (employer.getPassword().equals(password)) {
                return employer; // Нашли работодателя, возвращаем объект
            }
        }

        // 3. Никого не нашли или пароль неверный
        return null;
    }
}