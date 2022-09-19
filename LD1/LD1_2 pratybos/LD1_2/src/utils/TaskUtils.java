package utils;

import models.Project;
import models.Student;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class TaskUtils {

    /**
     * Finds all active projects
     * @param students List of all student objects
     * @return List of all active projects
     */
    public static List<String> FindAllActiveProjects(LinkedList<Student> students) {
        List<String> projects = new ArrayList<String>();

        for (Student item: students) {
            if (!projects.contains(item.projectName)) {
                projects.add(item.projectName);
            }
        }

        return projects;
    }

    /**
     * Finds all inactive projects
     * @param projectsLink List of all projects
     * @param activeProjects List of all active projects
     * @return List of all inactive projects
     */
    public static List<String> FindInactiveProjects(LinkedList<Project> projectsLink, List<String> activeProjects) {
        List<String> projectsList = new ArrayList<>();

        for (Project project: projectsLink) {
            if (!activeProjects.contains(project.name)) {
                projectsList.add(project.name);
            }
        }

        return projectsList;
    }

    /**
     * Gets all active professors
     * @param projectsLink List of all projects
     * @param inactiveProjects List of all inactive projects
     * @return all active professors
     */
    public static LinkedList<Project> GetAllActiveProfessors(LinkedList<Project> projectsLink, List<String> inactiveProjects) {
        LinkedList<Project> professors = new LinkedList<Project>();
        List<String> profName = new ArrayList<String>();

        for (var project: projectsLink) {
            String tempProfessor = project.profSurname + " " + project.profName;

            if (!inactiveProjects.contains(project.name) && !profName.contains(tempProfessor)) {
                profName.add(tempProfessor);
                professors.add(project);
            }
        }

        return professors;
    }

    /**
     * Finds the professor with most projects
     * @param projects list of all projects
     * @return returns the professors name
     */
    public static String MostProjects(LinkedList<Project> projects) {
        List<String> professors = new ArrayList<String>();
        String professor = "";
        int count = -1;

        for (var project: projects) {
            String tempProfessor;
            tempProfessor = project.profSurname + " " + project.profName;
            professors.add(tempProfessor);
        }

        for (int i = 0; i < professors.stream().count(); i++) {
            int tempcount = -1;
            String tempProfessor = professors.get(i);

            for (int j = 0; j < professors.stream().count(); j++) {
                String jProfessor = professors.get(j);
                if (tempProfessor == jProfessor) {
                    tempcount++;
                }
            }

            if (tempcount > count) {
                professor = tempProfessor;
                count = tempcount;
            }

        }

        return professor;
    }

    /**
     * Gets all project names by professor name and surname
     * @param professor professor surname and name
     * @param projects list of all projects
     * @return all projects of given professor
     */
    public static List<String> GetProjectsByProfessor(String professor, LinkedList<Project> projects) {
        String[] profSplit = professor.split(" ");
        String profSurname = profSplit[0];
        String profName = profSplit[1];

        List<String> projectNames = new ArrayList<String>();

        for (var project: projects) {
            if (project.profName.equals(profName) && project.profSurname.equals(profSurname)) {
                projectNames.add(project.name);
            }
        }

        return projectNames;
    }

}
