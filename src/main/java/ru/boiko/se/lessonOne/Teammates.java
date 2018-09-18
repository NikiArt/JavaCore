package ru.boiko.se.lessonOne;

public class Teammates {
    public String name;
    public double jump;
    public double run;
    public boolean endCourse;


    public Teammates(String playerName, double jump, double run) {
        this.name = playerName;
        this.run = run;
        this.jump = jump;
        this.endCourse = true;
    }
}
