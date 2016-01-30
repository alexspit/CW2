/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OCR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Dell
 */
public class KMeans {
    
    private List<Character> meanList;
    private List<Character> trainingList;
    private List<Character> testingList;
    private KNearestNeighbour knn;

    public KMeans(List<Character> trainingList, List<Character> testingList) {
        this.trainingList = trainingList;
        this.testingList = testingList;       
        this.knn = new KNearestNeighbour(trainingList, testingList);
        this.meanList = new ArrayList<Character>();
        
        for (Double d : knn.getNameList()) {
            meanList.add(new Character(d, trainingList.get(0).getSize()));
        }
        
        Collections.sort(meanList, new NameComparator());
        
        setMeans();
        
        System.out.println("Running K-Means...");
        KNearestNeighbour newKnn = new KNearestNeighbour(meanList, testingList, 1, false);
    }
    
    public void setMeans(){
        for (Character meanCharacter : meanList) {
            //System.out.println((int) meanCharacter.getName()+": "+knn.getListByChar(meanCharacter.getName()).size());
            for (Character character : knn.getListByChar(meanCharacter.getName())) {
                for (int i = 0; i < character.getSize(); i++) {
                    double newValue = meanCharacter.getValue(i);
                    newValue += character.getValue(i);
                    meanCharacter.setValue(i, newValue);                 
                }
            }
                        
            for (int i = 0; i < meanCharacter.getSize(); i++) {
                double meanValue = meanCharacter.getValue(i) / knn.getListByChar(meanCharacter.getName()).size();
                meanCharacter.setValue(i, meanValue);
            }
        }
        
        //System.out.println(meanList);
    
    }

    
    
    
    
    
}
