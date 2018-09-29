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
public class progressiveRate implements RateStrategy {

    private int calculateRate;
    
    public progressiveRate() {
        this.calculateRate = 0;
    }
    
    @Override
    public int calculateRate(int insertedSoFar) {
        calculateRate = insertedSoFar / 10 * 2;
        return calculateRate;
        
    }
}
