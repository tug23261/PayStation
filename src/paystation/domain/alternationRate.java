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
    
    private RateStrategy p=new progressiveRate();
    private RateStrategy l=new linearRate();
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
            calculateRate = p.calculateRate(insertedSoFar);
        }
        else {
            calculateRate = l.calculateRate(insertedSoFar);
        }
        return calculateRate;
        
    }
}
