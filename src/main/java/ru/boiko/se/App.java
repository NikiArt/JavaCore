package ru.boiko.se;

public class App 
{
    public static void main(String[] args) {
        Team team = new Team(4);    //создаем команду
        team.addTeammate(new Teammates("Piter", 1, 400 ));
        team.addTeammate(new Teammates("Homer", 1.5, 500 ));
        team.addTeammate(new Teammates("Jhon", 0.8, 600 ));
        team.addTeammate(new Teammates("Matt", 1.2, 300 ));
        team.showTeamList();

        Course c = new Course(4); // Создаем полосу препятствий
        c.addTrial(new Trial("run", 100));
        c.addTrial(new Trial("jump", 1.1));
        c.addTrial(new Trial("run", 200));
        c.addTrial(new Trial("run", 100));

        c.doIt(team); // Просим команду пройти полосу
        team.showResults(); // Показываем результаты
    }
}
