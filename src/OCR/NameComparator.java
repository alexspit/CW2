/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OCR;

import java.util.Comparator;
/**
 *
 * @author Dell
 */
public class NameComparator implements Comparator<Character>{

    @Override
    public int compare(Character c1, Character c2) {
        return c1.getName() < c2.getName() ? -1 : c1.getName() == c2.getName() ? 0 : 1;
    }
    
}
