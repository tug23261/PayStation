  package paystation.domain;

import java.util.Map;
import java.util.HashMap;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    
    
    private int insertedSoFar;
    private int timeBought;
    HashMap<Integer,Integer> coinMap = new HashMap<>();
    private    int count25 ;
    private    int count10 ;
    private    int count5 ;
    private    int deposit;
    
    static RateStrategy rs;
    

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            
            case 5:{
			count5 =+ count5 + 1;
            coinMap.put(5, count5);
            } break;
            case 10: {
			count10 =+ count10 + 1;
            coinMap.put(10, count10);
            }break;
            case 25: {
            count25 =+ count25 + 1;
			coinMap.put(25, count25);
            } break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = rs.calculateRate(insertedSoFar);
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        deposit += insertedSoFar;
        reset();
        return r;
    }

    public Map<Integer,Integer> cancel() {
        
        HashMap<Integer,Integer> tempMap = new HashMap<>();
        tempMap.putAll(coinMap);
        reset();
        return tempMap;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        coinMap.clear();
    }
    
    public int empty(){
        int total = deposit;
        deposit =0;
        reset();
        return total;
	
    } 
    
    
}
