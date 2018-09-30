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
    public String dayOfWeek;
    
    public alternationRate(String day) {
        this.calculateRate = 0;
        this.dayOfWeek =day;
    }
    
    public void set_dayOfWeek(String day){
        this.dayOfWeek =day;
    }
    
   
    @Override
    public int calculateRate(int insertedSoFar) {
        dayOfWeek = dayOfWeek.toLowerCase().trim();
        if (dayOfWeek.equalsIgnoreCase("weekend")){
            calculateRate = insertedSoFar / 5 * 2;
        }
        else {
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
        }
        return calculateRate;
        
    }
}
