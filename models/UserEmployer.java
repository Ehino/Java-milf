package models;

public class UserEmployer extends Employer {

    public UserEmployer(String name, String password, String city, String companyName, String jobDescribe, String girlType, String requirements, boolean advertStatus) {
        super(name, password, city, companyName, jobDescribe, girlType, requirements, advertStatus);
    }


    @Override
    public String getInfo() {
        return "Имя: " + name +
        "\nПароль: " + password +
        "\nГород: " + city +
        "\nНазвание компании: " + companyName +
        "\nОписание вакансии: " + jobDescribe +
        "\nТребуемый тип девушки: " + girlType +
        "\nТребования к девушке: " + requirements +
        "\nСтатус объявления: " + (advertStatus? "Актуально" : "Не актуально");
    }
}