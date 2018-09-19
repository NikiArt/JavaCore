package ru.boiko.se.lessonone;

public class Course {
    private Trial[] course;
    private int volTrial;

    public void addTrial(Trial trial) {
        if(volTrial < course.length) {
            course[volTrial] = trial;
            volTrial++;
        } else {
            System.out.println("Превышено количество испытаний на полосе препятствий");
        }
    }

    public Course(int courseLength) {
        course = new Trial[courseLength];
        volTrial = 0;
    }

    public void doIt(Team team) {
        for (Teammates t : team.getTeam()) {
            double maxDistance = 0;
            for (Trial c : course) {
                if ("jump".equals(c.action)) {
                    if (c.length > t.jump) {
                        t.endCourse = false;
                    }
                } else  if ("run".equals(c.action)) {
                    maxDistance += c.length;
                }
                if (maxDistance > t.run) {
                    t.endCourse = false;
                }
            }
        }
    }
}
