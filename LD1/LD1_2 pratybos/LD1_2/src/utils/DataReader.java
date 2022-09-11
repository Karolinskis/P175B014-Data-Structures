package utils;

import models.Project;
import models.Student;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class DataReader {

    public static LinkedList<Project> ReadProjectFile(String fileName) {
        LinkedList<Project> list = new LinkedList<Project>();

        FileInputStream fileStream = null;
        Scanner scanner = null;

        try {
            fileStream = new FileInputStream(fileName);
            scanner = new Scanner(fileStream, "UTF8");
            while (scanner.hasNextLine()) {
                String[] lines = scanner.nextLine().split(",");
                list.add(new Project(lines[0], lines[1], lines[2], Integer.parseInt(lines[3])));
            }
            fileStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return list;
    }

    public static LinkedList<Student> ReadStudentFile(String fileName) {
        LinkedList<Student> list = new LinkedList<Student>();

        FileInputStream fileStream = null;
        Scanner scanner = null;

        try {
            fileStream = new FileInputStream(fileName);
            scanner = new Scanner(fileStream, "UTF8");
            while (scanner.hasNextLine()) {
                String[] lines = scanner.nextLine().split(",");
                list.add(new Student(lines[0], lines[1], lines[2], lines[3]));
            }
            fileStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return list;
    }

}
