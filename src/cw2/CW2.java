/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class CW2 {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        
        String filename = args[0];
      
        File file = new File(filename);
        HashMap<Integer, ArrayList<ArrayList<Integer>>> characterList = new HashMap<>();
        HashMap<Integer, ArrayList<ArrayList<Integer>>> meansHash = new HashMap<>();
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {         
               
                
                String newLine = scanner.nextLine();
                String[] split = newLine.split(",");
                 System.out.println(newLine);
                ArrayList<Integer> imageValues = new ArrayList<>();
                
                for (String string : split) {
                    imageValues.add(Integer.parseInt(string));
                }
                
                int key = imageValues.get(imageValues.size()-1);
                
                imageValues.remove(imageValues.size()-1);
                
                if(characterList.containsKey(key)){
                    characterList.get(key).add(imageValues);
                }
                else{                                   
                    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
                    list.add(imageValues);
                    characterList.put(key, list);
                }
                          
            }
        }
        
        
        for (int key : characterList.keySet()) {
            
            System.out.println(key);
            for (ArrayList<Integer> list : characterList.get(key)) {
                System.out.println(list);
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            System.out.println();
        }
        
        
        //Means Array position 0
        ArrayList<Double> meanArray0 = new ArrayList<>();
        
        for (int i = 0; i < characterList.get(0).get(0).size(); i++) {
            meanArray0.add(i, 0.0);
        }
       
        int listSize = 0;
        int positionOneTotal = 0;
        
        for (ArrayList<Integer> list : characterList.get(0)) {
            
            int listPosition = 0;
            
            for (Integer integer : list) {
                           
                meanArray0.set(listPosition, integer+meanArray0.get(listPosition));
                listPosition++;
            }
            listSize++;                 
        }
        
        for (int i = 0; i < meanArray0.size(); i++) {
            
            meanArray0.set(i, meanArray0.get(i)/listSize);
        }
        
        System.out.println("Means0: "+meanArray0);                 
        System.out.println("Size0: "+listSize);
        
         //Means Array position 1
        ArrayList<Double> meanArray1 = new ArrayList<>();
        
        for (int i = 0; i < characterList.get(1).get(0).size(); i++) {
            meanArray1.add(i, 0.0);
        }
       
        listSize = 0;
              
        for (ArrayList<Integer> list : characterList.get(1)) {
            
            int listPosition = 0;
            
            for (Integer integer : list) {
                           
                meanArray1.set(listPosition, integer+meanArray1.get(listPosition));
                listPosition++;
            }
            listSize++;                 
        }
        
        for (int i = 0; i < meanArray1.size(); i++) {
            
            meanArray1.set(i, meanArray1.get(i)/listSize);
        }
        
        System.out.println("Means1: "+meanArray1);                 
        System.out.println("Size1: "+listSize);
        
        
        
        ArrayList<Integer> testArray = new ArrayList<>();
        testArray.add(0);
        testArray.add(0);
        testArray.add(14);
        testArray.add(15);
        testArray.add(3);
        testArray.add(2);
        
        
        double distanceFrom0 = Math.sqrt( Math.pow(testArray.get(0)-meanArray0.get(0), 2) +  Math.pow(testArray.get(1)-meanArray0.get(1), 2) +  Math.pow(testArray.get(2)-meanArray0.get(2), 2) +
                                          Math.pow(testArray.get(3)-meanArray0.get(3), 2) +  Math.pow(testArray.get(4)-meanArray0.get(4), 2) +  Math.pow(testArray.get(5)-meanArray0.get(5), 2)
                                        );
        double distanceFrom1 = Math.sqrt( Math.pow(testArray.get(0)-meanArray1.get(0), 2) +  Math.pow(testArray.get(1)-meanArray1.get(1), 2) +  Math.pow(testArray.get(2)-meanArray1.get(2), 2) +
                                          Math.pow(testArray.get(3)-meanArray1.get(3), 2) +  Math.pow(testArray.get(4)-meanArray1.get(4), 2) +  Math.pow(testArray.get(5)-meanArray1.get(5), 2)
                                        );

       
        
        System.out.println("Distance from 0: "+distanceFrom0);
        System.out.println("Distance from 1: "+distanceFrom1);
        
        
    }
    
}
