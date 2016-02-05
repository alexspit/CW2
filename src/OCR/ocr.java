/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OCR;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
        
        List<Character> trainList = new ArrayList<>();
        List<Character> testList = new ArrayList<>();
        List<Character> fullList = new ArrayList<>();

        try (Scanner scanner = new Scanner(trainingFile)) {
            while (scanner.hasNext()) {         
                String newLine = scanner.nextLine();
                trainList.add(new Character(newLine));         
            }
        }
        
        try (Scanner scanner = new Scanner(testFile)) {
            while (scanner.hasNext()) {         
                String newLine = scanner.nextLine();
                testList.add(new Character(newLine));         
            }
        }  
        
        fullList.addAll(trainList);
        fullList.addAll(testList);
                
        //-----------Running K-Means---------------//
        double[] MeansPercentages = new double[5];
        System.out.println("Running K-Means...");
        
        for (int j = 0; j < 5; j++) {
                
            long seed = System.nanoTime();
            Collections.shuffle(fullList, new Random(seed));

            trainList.clear();
            testList.clear();

            for (int i = 0; i < fullList.size(); i++) {

                if(i > fullList.size()/3){

                    trainList.add(fullList.get(i));
                }
                else{
                    testList.add(fullList.get(i));
                   }
                }   
                
                KMeans km = new KMeans(trainList, testList);
                MeansPercentages[j] = km.run();
                System.out.println("Run "+(j+1)+" Correctly Classified: "+MeansPercentages[j]+"%");           
        }
        
        double MeansTotal = 0.0;
        for (double percentage : MeansPercentages) {
            MeansTotal += percentage;
        }

        System.out.println("Average correctly classified: "+MeansTotal/MeansPercentages.length+"%"); 
        System.out.println("-----------------------------------------");    
        
        //--------------Running K-NN------------------??
        double[] NNPercentages = new double[5];
        
        for (int k = 1; k <= 11; k++) {
            
            if(k==1){
                System.out.println("Running Nearest Neighbour...");
            }else{
                System.out.println("Running K-Nearest Neighbour (K="+k+")...");
            }
            for (int j = 0; j < 5; j++) {
                
                long seed = System.nanoTime();
                Collections.shuffle(fullList, new Random(seed));

                trainList.clear();
                testList.clear();

                for (int i = 0; i < fullList.size(); i++) {

                   if(i > fullList.size()/3){

                       trainList.add(fullList.get(i));
                   }
                   else{
                       testList.add(fullList.get(i));
                   }
                }   
                
                KNearestNeighbour knn = new KNearestNeighbour(trainList, testList);
                NNPercentages[j] = knn.run(k);
                System.out.println("Run "+(j+1)+" Correctly Classified: "+NNPercentages[j]+"%");           
            }
        
            double NNtotal = 0.0;
            for (double percentage : NNPercentages) {

                NNtotal += percentage;
            }

            System.out.println("Average correctly classified: "+NNtotal/NNPercentages.length+"%"); 
            System.out.println("-----------------------------------------");
            
        }
        
        
        
        
        /*System.out.println("-------------------------------------------");
        System.out.println("Training Dataset Size: "+trainList.size()+" \nTesting Dataset Size: "+testList.size()+"\nTotal Dataset Size: "+fullList.size());
        System.out.println("-------------------------------------------");
        KMeans km = new KMeans(trainList, testList);   
        System.out.println("-------------------------------------------");
        for (int i = 1; i <= 10; i++) {
            KNearestNeighbour knn = new KNearestNeighbour(trainList, testList, i, true);
            knn.run(i);
            System.out.println("-------------------------------------------");
        }  */
                          
    }   
}
