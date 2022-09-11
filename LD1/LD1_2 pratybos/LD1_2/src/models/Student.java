package models;

import java.util.Objects;

public class Student {
    public String projectName;
    public String surname;
    public String name;
    public String group;

    public Student(){}

    public Student(String projectName, String surname, String name, String group) {
        this.projectName = projectName;
        this.surname = surname;
        this.name = name;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return projectName.equals(student.projectName) &&
                surname.equals(student.surname) &&
                name.equals(student.name) &&
                group.equals(student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, surname, name, group);
    }
}
