/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OCR;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author Alex
 */
public class OCR {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        String trainingListFileName = "training.txt";
        String testListFileName = "testing.txt";
        
        try {
            trainingListFileName = args[0];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No training file supplied, using default training set...");
        }
        
        try {
            testListFileName = args[1];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No testing file supplied, using default testing set...");
        }
        
      
        File trainingFile = new File(trainingListFileName);
        File testFile = new File(testListFileName);
        
        List<Character> characterList = new ArrayList<>();
        List<Character> testList = new ArrayList<>();

        try (Scanner scanner = new Scanner(trainingFile)) {
            while (scanner.hasNext()) {         
                String newLine = scanner.nextLine();
                characterList.add(new Character(newLine));         
            }
        }
        
        try (Scanner scanner = new Scanner(testFile)) {
            while (scanner.hasNext()) {         
                String newLine = scanner.nextLine();
                testList.add(new Character(newLine));         
            }
        }   
        
        KMeans km = new KMeans(characterList, testList);
        System.out.println("-------------------------------------------");
        for (int i = 1; i <= 10; i++) {
            KNearestNeighbour knn = new KNearestNeighbour(characterList, testList, i, true);
            System.out.println("-------------------------------------------");
        }        
    }   
}
