/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OCR;

import java.util.Comparator;

/**
 *
 * @author Alex
 */
public class DistanceComparator implements Comparator<Character>{

    @Override
    public int compare(Character c1, Character c2) {
        return c1.getDistance() < c2.getDistance() ? -1 : c1.getDistance() == c2.getDistance() ? 0 : 1;
    }
    
}
