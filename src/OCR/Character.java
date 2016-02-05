/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OCR;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Character {
    
    private double name;
    private ArrayList<Double> values;
    private double distance;

    public Character(double name, ArrayList<Double> values) {
        this.name = name;
        this.values = values;
       
    } 
    
    public Character(double name, int valueSize) {
        this.name = name;
        this.values = new ArrayList<>();
               
        for (int i = 0; i < valueSize; i++) {
            this.values.add(0.0);
        }
           
    } 
         
    public Character(String newLine) {
        
        this.values = new ArrayList<>();
        
        String[] split = newLine.split(",");
                        
        for (String string : split) {
            this.values.add(Double.parseDouble(string));
        }
                
        this.name = this.values.get(this.getSize()-1);
                
        this.values.remove(this.getSize()-1);
                               
    } 
     
    public double getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

    public int getSize(){
        return this.values.size();
    }
    
    public double getValue(int key){
        return this.values.get(key);
    }
    
    public void setValue(int key, double value){
        this.values.set(key, value);
    }
    
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return (int) this.name+": "+this.values+" Distance: "+this.getDistance()+"\n";
    }
    
    
    
    
    
}
