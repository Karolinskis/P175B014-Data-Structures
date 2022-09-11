import models.*;
import utils.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String projectPath = "U22a.txt";
        String studentPath = "U22b.txt";

        String professorResult = "ProfRez.txt";
        String projectsResult = "ProjRez.txt";

        // Read Both files
        LinkedList<Project> projectsData = new LinkedList<Project>();
        projectsData = DataReader.ReadProjectFile(projectPath);

        LinkedList<Student> studentsData = new LinkedList<Student>();
        studentsData = DataReader.ReadStudentFile(studentPath);


        // Getting professor name
        System.out.println("Įveskite profesoriaus vardą, pavardę:");
        Scanner in = new Scanner(System.in);
        String professor = in.nextLine();

        // Most projects
        String mostProjects = TaskUtils.MostProjects(projectsData);
        System.out.println("Daugiausiai projektinių darbų turintis dėstytojas: ");
        System.out.println(mostProjects);

        // Active Professors
        List<String> activeProjects = TaskUtils.FindAllActiveProjects(studentsData);
        List<String> inactiveProjects = TaskUtils.FindInactiveProjects(projectsData, activeProjects);
        LinkedList<Project> activeProfessors = TaskUtils.GetAllActiveProfessors(projectsData, inactiveProjects);

        DataWriter.WriteProfessorsToFile(professorResult, activeProfessors);

        // Given professor projects
        List<String> selectedProfessorProjects = TaskUtils.GetProjectsByProfessor(professor, projectsData);
        DataWriter.WriteProjectsToFile(projectsResult, selectedProfessorProjects);


    }
}