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
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {         
                
                String newLine = scanner.nextLine();
                String[] split = newLine.split(",");
                
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
        
        
    }
    
}
