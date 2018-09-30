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
        int money = insertedSoFar;
        
            if (money >= 350){
                money -= 350;
                calculateRate = 120 + money /5;
            }
            else if(money >= 150 ){
                money -= 150;
                calculateRate = 60 + money*3 /10;
            }
            else{
                calculateRate = insertedSoFar / 5 * 2;
            }
        
        return calculateRate;
        
    }
}
