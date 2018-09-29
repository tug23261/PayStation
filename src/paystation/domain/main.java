/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.Scanner;
import static paystation.domain.PayStationImpl.rs;

/**
 *
 * @author ishan
 */
public class main {
    public static void main(String [] args) throws IllegalCoinException
	{   PayStation ps;
            rs = new linearRate();
            ps = new PayStationImpl();
            Scanner kb = new Scanner (System.in);
            int input;
            do{
            System.out.print("select a choice:\n" +
                            "[1] Deposit Coins\n" +
                            "[2] Display\n" +
                            "[3] Buy Ticket\n" +
                            "[4] Cancel\n" +
                            "[5] Change Rate Strategy\n"+
                            "to end enter any number greater than 5.\n");
            input = kb.nextInt();
            switch (input) {
            case 1:{
                int coin;
                System.out.print("input a coin [5, 10, 25]: \n"+
                                "To finish adding coins enter 0: \n");
                coin = kb.nextInt();
                
                while (coin != 0){
                    ps.addPayment(coin);
                    coin = kb.nextInt();
                }
                System.out.print("\n");
                
            } break;
            
            case 2:{
                System.out.print("Time Bought is: "+ ps.readDisplay()+"\n\n");
            } break;
            
            case 3:{
                ps.buy();
            } break;
            
            case 4:{
                ps.cancel();
            } break;
            
            case 5:{
                int rate_type;
                System.out.print("Select rate type:\n"+
                                "[1] linear rate\n"+
                                "[2] Progressive rate\n"+
                                "[3] alternation rate\n"+
                                "[0] to go back\n");
                rate_type = kb.nextInt();
                switch(rate_type){
                    case 0:{
                        
                    }break;
                    
                    case 1:{
                        rs = new linearRate();
                    }break;
                    
                    case 2:{
                        rs = new progressiveRate();
                    }break;
                    
                    case 3:{
                        rs = new alternationRate();
                    }break;
                    
                }
                
            }
            
        }
            }while (input <6);
	    System.out.println("Thank you for using PayStation!\n");
	}
}
