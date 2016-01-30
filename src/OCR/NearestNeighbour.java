/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OCR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Alex
 */
public class NearestNeighbour {

    private List<Character> characterList;
    private List<Character> testList;
    private List<Double> nameList;
    private int k;
    //private Character testCharacter;
    
    
    NearestNeighbour(List<Character> trainingList, Character testCharacter, int k) {
        this.characterList = trainingList;
        this.k = k;
        //this.testCharacter = testCharacter;       
        this.nameList = this._getNameList();
    }
    
    
    NearestNeighbour(List<Character> trainingList, List<Character> testList, int k) {
        this.characterList = trainingList;
        this.k = k;
        this.testList = testList;       
        this.nameList = this._getNameList();
        
        System.out.println("Running K-Nearest Neighbours (K="+k+")...");
        
        double testListSize = testList.size();
        double counter = 0;
        for (Character character : testList) {
                       
            double classification = this.classify(character);
            
            if(character.getName() == classification){
                counter++;               
            }
            
        }
        
        System.out.println("Correctly Classified: "+counter);
        System.out.println("Total testing set: "+testListSize);
        double correctlyClassified = (counter/testListSize)*100;
        System.out.println(correctlyClassified);
        
    }
    
      
    public void calcDistances(Character testChar){
        
        for (Character character : characterList) {
            double dist = 0.0;
            
            for (int i = 0; i < character.getSize(); i++) {
                dist += Math.pow(character.getValue(i) - testChar.getValue(i), 2);
            }
            
            double distance = Math.sqrt(dist);
            character.setDistance(distance);              
        }
            
    }

    public List<Character> getCharacterList() {
        return characterList;
    }

    public void printCharacterList(){
        for (Character character : characterList) {
            System.out.println(character);
        }
    }
    
    public void orderList(){
        Collections.sort(characterList, new DistanceComparator());
    }
    
    public List<Character> getKList(){
        
        List<Character> KList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            KList.add(characterList.get(i));
        }
        return KList;
    
    }
    
    public double classify(Character testChar){
        double classification = -1;
        
        this.calcDistances(testChar);
        this.orderList();
        
        List<Character> kList = this.getKList();
        
        ArrayList<Double> totalList = new ArrayList<>();
        
        for (int i = 0; i < this.getDifferentCharCount(); i++) {            
            totalList.add(0.0);
        }
             
        for (int i = 0; i < kList.size(); i++) {
            totalList.set((int) kList.get(i).getName(), totalList.get((int) kList.get(i).getName())+1);
        }
        
        //System.out.println(totalList);
        
        double highestCount = totalList.get(0);
        for (int counter = 1; counter < totalList.size(); counter++) {
            if (totalList.get(counter) > highestCount) {
                highestCount = totalList.get(counter);
            }
        }
            
        //System.out.println("max # of occurences: "+highestCount);
        
        //Checking the frequency of the max count
        int freq = 0;
	for (int counter = 0; counter < totalList.size(); counter++) {
            if (totalList.get(counter) == highestCount) {
                freq++;
            }
	}
        
        //System.out.println("Frequency: "+freq);
        
        
        if(freq == 1){
            for (int counter = 0; counter < totalList.size(); counter++) {
                if(totalList.get(counter) == highestCount){
                    classification = counter;
                    return classification;
                }
            }
                   
        }
        else{//There are multiple instances of that highestCount
        
            double[] majorityIndexes = new double[freq];//array of indices of modes
            //System.out.println("Multiple Majority Classes: "+freq+" classes");
            //System.out.println("---------------------------------------------------------------------------------------");
            int index = 0;
            for (int counter = 0; counter < totalList.size(); counter++) {
                if(totalList.get(counter) == highestCount){
                    majorityIndexes[index] = counter;
                    index++;
                }
            }
            
            //now choose one at random
            Random rnd = new Random();        
            //get random number 0 <= rIndex < size of ix
            int randomIndex = rnd.nextInt(majorityIndexes.length);
            //System.out.println("Random index: "+randomIndex);
            
            return majorityIndexes[randomIndex];
                        
        }
        /*int highestCount = 0;
        for (int i = 0; i < totalList.size(); i++) {
            if(highestCount < totalList.get(i)){
                highestCount = totalList.get(i);
                name = i;
            }
        }*/
        
        return classification;
        
    }
    
    public List<Character> getListByChar(int name){
        
        List<Character> list = new ArrayList<>();
        for (Character character : characterList) {
            if(character.getName() == name){
                list.add(character);
            }
        }
        return list;  
    }
    
    public int getDifferentCharCount(){
        return nameList.size();
    }
    
    private List<Double> _getNameList(){
        List<Double> totalList = new ArrayList<>();
        
        for (Character character : characterList) {
            if(totalList.isEmpty()){
                totalList.add(character.getName());
            }
            boolean found = false;
            for (Double d : totalList) {
               
                if( character.getName() == d){
                    found = true;
                    break;
                }               
            }
            
            if(!found){
                totalList.add(character.getName());
            }
        }
        return totalList;
    }
    
    public List<Double> getNameList(){
    
        return nameList;
    }


    public int getK() {
        return k;
    }

    
    
    
    
}
