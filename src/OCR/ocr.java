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
 * @author Dell
 */
public class ocr {
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        
        String trainingListFileName = args[0];
        String testListFileName = args[1];
      
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
        
        NearestNeighbour knn = new NearestNeighbour(characterList, testList, 1);
                
        //nn.calcDistances();
       // nn.orderList();
        
       // System.out.println(nn.getKList());
       // nn.printCharacterList();
        
        //System.out.println(nn.getKList());
        //System.out.println(nn.getResultList());
        
      
       

    }
    
}
