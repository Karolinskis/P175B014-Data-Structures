package utils;

import models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DataWriter {
    /**
     * Writes all given professors to corresponding file
     * @param filePath file to write to
     * @param activeProfessors projects to write
     */
    public static void WriteProfessorsToFile(String filePath, LinkedList<Project> activeProfessors) {
        try{
            FileWriter profWriter = new FileWriter(filePath);
            profWriter.write("Pavardė Vardas \n");
            for (var activeProfessor: activeProfessors) {
                String profName = activeProfessor.profName;
                String profSurname = activeProfessor.profSurname;
                String profFull = profSurname + " " + profName + '\n';
                profWriter.write(String.format(profFull));
            }
            profWriter.close();
            System.out.println("WRITER: Successfully wrote proffesors to file.");
        }
        catch (IOException e) {
            System.out.println("WRITER: An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Writes all given project data to corresponding file
     * @param filePath file to write to
     * @param activeProjects projects to write
     */
    public static void WriteProjectsToFile(String filePath, List<String> activeProjects) {
        try {
            FileWriter projWriter = new FileWriter(filePath);
            projWriter.write("Projekto pavadinimas \n");
            for (var activeProject: activeProjects) {
                projWriter.write(activeProject + '\n');
            }
            projWriter.close();
            System.out.println("WRITER: Succesfully wrote projects to file.");
        }
        catch (IOException e) {
            System.out.println("WRITER: An error occurred.");
            e.printStackTrace();
        }
    }
}
