package models;

import java.util.Objects;

public class Project {
    public String name;
    public String profSurname;
    public String profName;
    public Integer givenTime;

    public Project(String name, String profSurname, String profName, Integer givenTime) {
        this.name = name;
        this.profSurname = profSurname;
        this.profName = profName;
        this.givenTime = givenTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return name.equals(project.name) &&
                profSurname.equals(project.profSurname) &&
                profName.equals(project.profName) &&
                givenTime.equals(project.givenTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, profSurname, profName, givenTime);
    }
}
