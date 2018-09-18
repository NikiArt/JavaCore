package ru.boiko.se;

public class Team {
    private Teammates[] teammates;
    private int volTeam;

    public void addTeammate(Teammates teammate) {
        if(volTeam < teammates.length) {
            teammates[volTeam] = teammate;
            volTeam++;
        } else {
            System.out.println("Превышено допустимое количество участников в команде");
        }
    }

    public Team(int courseLength) {
        teammates = new Teammates[courseLength];
        volTeam = 0;
    }

    public Teammates[] getTeam() {
        return this.teammates;
    }

    public void showTeamList() {
        for( Teammates t : this.teammates) {
            System.out.println("" + t.name + " может пробежать " + t.run + "м. и перепрыгнуть препятствие высотой до " + t.jump + "м.");
        }
    }

    public void showResults() {
        System.out.println("\n");
        for( Teammates t : this.teammates) {
            if(t.endCourse) {
                System.out.println("" + t.name + " прошел полосу препятствий");
            }
        }
    }
}
