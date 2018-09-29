/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * @author ishan
 */
public class alternationRate implements RateStrategy {

    private int calculateRate;
    
    public alternationRate() {
        this.calculateRate = 0;
    }
    
    @Override
    public int calculateRate(int insertedSoFar) {
        calculateRate = insertedSoFar / 1 * 2;
        return calculateRate;
        
    }
}
