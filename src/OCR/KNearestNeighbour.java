package OCR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Alex
 */
public final class KNearestNeighbour {

    private List<Character> trainList;
    private List<Character> testList;
    private List<Double> nameList;
    private int k;
      
    
    KNearestNeighbour(List<Character> trainingList, Character testCharacter, int k) {
        this.trainList = trainingList;
        this.k = k;     
        this.nameList = this._getNameList();
    }
    
    
    KNearestNeighbour(List<Character> trainingList, List<Character> testList, int k) {
        this.trainList = trainingList;
        this.k = k;
        this.testList = testList;       
        this.nameList = this._getNameList();
        
        /*if(print){           
            if(k == 1){
                System.out.println("Running Nearest Neighbour...");
            }
            else{
                System.out.println("Running K-Nearest Neighbours (K="+k+")...");
            }           
        }
        
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
        System.out.println(correctlyClassified+"%");*/
        
    }
    
    KNearestNeighbour(List<Character> trainingList, List<Character> testList) {
        this.trainList = trainingList;
        this.testList = testList;       
        this.nameList = this._getNameList();              
    }
    
    /**
     * Calculates the euclidian distance between the given Character and the training set, and stores it in each Character 
     * @param testChar Character object used to compare the distance
     */
    public void calcDistances(Character testChar){
        
        for (Character character : trainList) {
            double dist = 0.0;
            
            for (int i = 0; i < character.getSize(); i++) {
                dist += Math.pow(character.getValue(i) - testChar.getValue(i), 2);
            }
            
            double distance = Math.sqrt(dist);
            character.setDistance(distance);              
        }
            
    }
    
    public double run(int k){
    
        this.k = k;
        
        double testListSize = testList.size();
        double counter = 0;
        for (Character character : testList) {
                       
            double classification = this.classify(character);
            
            if(character.getName() == classification){
                counter++;               
            }           
        }
        
        //System.out.println("Correctly Classified: "+counter);
        //System.out.println("Total testing set: "+testListSize);
        double correctlyClassified = (counter/testListSize)*100;
        //System.out.println(correctlyClassified+"%");
        
        return correctlyClassified;
    }

    /**
     * Getter method which returns the training list
     * @return List<Character> ArrayList of training Characters
     */
    public List<Character> getTrainingList() {
        return trainList;
    }

    /**
     * Outputs to console all the Characters in the training set
     */
    public void printTrainingList(){
        for (Character character : trainList) {
            System.out.println(character);
        }
    }
    
    /**
     * Sorts the training set in ascending order according to the distances
     */
    public void orderList(){
        Collections.sort(trainList, new DistanceComparator());
    }
    
    /**
     * Loops K times through the sorted training set and adds the Characters at the top to an ArrayList is than returned
     * @return List<Character> An ArrayList of the closes K Characters to the test object
     */
    public List<Character> getKList(){
        
        List<Character> KList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            KList.add(trainList.get(i));
        }
        return KList;
    
    }
    
    /**
     * This method takes a Character as input and calculated the euclidian distance for each object in the training set, orders this list in ascending order and gets a list of the K closest instances.
     * A Majority vote is taken to determine the classification of the character. In case there is no majority, the class with the shortest distance from the matching frequencies is chosen.
     * @param testChar The Character to classify
     * @return double The predicted class of the Character
     */
    public double classify(Character testChar){
        double classification = -1;
        
        this.calcDistances(testChar);
        this.orderList();
        
        //Arraylist to store to closest K instances
        List<Character> kList = this.getKList(); 
        //Arraylist to store the totals of each instance
        ArrayList<Double> totalList = new ArrayList<>();
        
        //Populating the totalList with a value of 0 for every different class 
        for (int i = 0; i < this.getDifferentCharCount(); i++) {            
            totalList.add(0.0);
        }
        
        //Looping through the list of closest instances and adding a count to that class's position in the totalList
        for (int i = 0; i < kList.size(); i++) {
            totalList.set((int) kList.get(i).getName(), totalList.get((int) kList.get(i).getName())+1);
        }
        
        //Finding the highest count in the totalList
        double highestCount = totalList.get(0);
        for (int counter = 1; counter < totalList.size(); counter++) {
            if (totalList.get(counter) > highestCount) {
                highestCount = totalList.get(counter);
            }
        }
                       
        //Checking the frequency of the highest count (To see if there is a majority)
        int freq = 0;
	for (int counter = 0; counter < totalList.size(); counter++) {
            if (totalList.get(counter) == highestCount) {
                freq++;
            }
	}
        
        //Frequancy of 1 means there is a majority
        if(freq == 1){
            for (int counter = 0; counter < totalList.size(); counter++) {
                if(totalList.get(counter) == highestCount){
                    classification = counter;
                    return classification;
                }
            }
                   
        }
        else{//There are multiple instances of the highest count
            
            //array of indices of modes
            double[] majorityIndexes = new double[freq];
            //adding the instances which are multiple to the array
            int index = 0;
            for (int counter = 0; counter < totalList.size(); counter++) {
                if(totalList.get(counter) == highestCount){
                    majorityIndexes[index] = counter;
                    index++;
                }
            }
            
            /*
            //choosing one at random
            Random rnd = new Random();                
            int randomIndex = rnd.nextInt(majorityIndexes.length);         
            return majorityIndexes[randomIndex];
            */   
           
            //Getting the instance with the shortest amongst the multiple instances
            double shortest = Double.MAX_VALUE;
            double shortestClass = 0;
            
            for (double majorityIndex : majorityIndexes) {
               
                for (Character c : kList) {
                    if(c.getName() == majorityIndex){
                        
                        if(c.getDistance() < shortest){
                            shortest = c.getDistance();
                            shortestClass = c.getName();
                        }
                    }
                }
            }
            
            return shortestClass;
        }
        
        return classification;
        
    }
    
    /**
     * Returns all the characters belonging to the given class
     * @param name The class of the Character
     * @return List<Character> A list of Characters belonging to that class
     */
    public List<Character> getListByChar(double name){
        
        List<Character> list = new ArrayList<>();
        for (Character character : trainList) {
            if(character.getName() == name){
                list.add(character);
            }
        }
        return list;  
    }
    
    /**
     * Get the total number of different classes
     * @return int Different class count
     */
    public int getDifferentCharCount(){
        return nameList.size();
    }
    
     
    /**
     * Get an ArrayList of unique classes
     * @return List<Double> Unique classes
     */
    private List<Double> _getNameList(){
        List<Double> totalList = new ArrayList<>();
        
        for (Character character : trainList) {
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
    
    /**
     * Getter which returns an ArrayList of all the unique classes
     * @return List<Double> nameList
     */
    public List<Double> getNameList(){
    
        return nameList;
    }

    /**
     * Getter which returns the value of K
     * @return int Value of K
     */
    public int getK() {
        return k;
    } 
    
}
