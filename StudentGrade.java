/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentgrade;

/**
 *
 * @author khan
 */
import java.util.Scanner;
import java.util.ArrayList;
public class StudentGrade {

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();

        System.out.println("Enter student grades (type -1 to finish):");
        while (true) {
            double grade = sc.nextDouble();
            if (grade == -1) {
                break;
            }
            grades.add(grade);
        }

        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            double sum = 0, highest = grades.get(0), lowest = grades.get(0);
            for (double grade : grades) {
                sum += grade;
                if (grade > highest) highest = grade;
                if (grade < lowest) lowest = grade;
            }
            double average = sum / grades.size();

            System.out.println("\nGrade Summary:");
            System.out.printf("Average Score: %.2f\n", average);
            System.out.println("Highest Score: " + highest);
            System.out.println("Lowest Score: " + lowest);
        }

        sc.close();
    }
}

    

