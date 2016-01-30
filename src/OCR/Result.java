/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OCR;

/**
 *
 * @author Dell
 */
public class Result {
    
    private double distance;
    private int character;
    
    public Result(int character, double distance){
        this.character = character;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "Character: "+character+" Distance: "+distance+"\n";
    }
    
    
    
    
    
}
