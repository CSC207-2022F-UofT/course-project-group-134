package entities;
import java.io.*;
import java.util.Scanner;

import entities.menu_factories.*;

public class MenuFactory {
    /*private NewCollegeMenuFactory newCollegeMenuFactory;
    private UniversityCollegeMenuFactory universityCollegeMenuFactory;
    private StMikesCollegeMenuFactory stMikesCollegeMenuFactory;
    private VictoriaCollegeMenuFactory victoriaCollegeMenuFactory;
    private TrinityCollegeMenuFactory trinityCollegeMenuFactory;
    private WoodsworthCollegeMenuFactory woodsworthCollegeMenuFactory;
    private InnisCollegeMenuFactory innisCollegeMenuFactory;
    private ChestnutMenuFactory chestnutMenuFactory;
    private CampusOneMenuFactory campusOneMenuFactory;*/

    public static void main(String[] args) throws Exception
    {
//parsing a CSV file into Scanner class constructor
        Scanner sc = new Scanner(new File("/Users/titanv/Desktop/Menu2.csv"));
        sc.useDelimiter(",");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            System.out.println(sc.next());  //find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner
    }




}
