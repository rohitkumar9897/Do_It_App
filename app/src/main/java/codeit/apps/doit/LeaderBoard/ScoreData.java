package codeit.apps.doit.LeaderBoard;

public class ScoreData {
    String age, country, name, username;
    int dailyScore, monthlyScore, weeklyScore;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ScoreData() {
        // Default constructor
    }

    public ScoreData(String age, String country, String name, String username, int dailyScore, int monthlyScore, int weeklyScore) {
        this.age = age;
        this.country = country;
        this.name = name;
        this.username = username;
        this.dailyScore = dailyScore;
        this.monthlyScore = monthlyScore;
        this.weeklyScore = weeklyScore;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDailyScore() {
        return dailyScore;
    }

    public void setDailyScore(int dailyScore) {
        this.dailyScore = dailyScore;
    }

    public int getMonthlyScore() {
        return monthlyScore;
    }

    public void setMonthlyScore(int monthlyScore) {
        this.monthlyScore = monthlyScore;
    }

    public int getWeeklyScore() {
        return weeklyScore;
    }

    public void setWeeklyScore(int weeklyScore) {
        this.weeklyScore = weeklyScore;
    }
}
