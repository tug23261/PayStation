/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import static java.lang.System.exit;
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
            int input = 1;
            
            while (input !=0){
                System.out.print("select a choice:\n" +
                                "[1] Deposit Coins\n" +
                                "[2] Display\n" +
                                "[3] Buy Ticket\n" +
                                "[4] Cancel\n" +
                                "[5] Change Rate Strategy\n"+
                                "to EXIT enter 0.\n");
                input = kb.nextInt();
                    System.out.println("\n");
                switch (input) {
                    case 0:{
                        System.out.println("\nThank you for using PayStation!\n");
                        exit(0);
                    }
                    case 1:{
                        int coin =1;
                        System.out.print("input a coin [5, 10, 25]: \n"+
                                        "To finish adding coins enter 0: \n");
                        while(coin != 0){
                            coin = kb.nextInt();
                            switch (coin){
                                case 0:{
                                    System.out.print("\n");
                                    }break;
                                case 5:{
                                    ps.addPayment(coin);
                                    }break;
                                case 10:{
                                    ps.addPayment(coin);
                                    }break;
                                case 25:{
                                    ps.addPayment(coin);
                                    }break;
                                default:
                                    System.out.println("coin not acceptable. Input a valid coin");
                            }
                        }
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
                        System.out.println("\n");
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
                                int day = 0;
                                while (day != 1 || day != 2){
                                    System.out.println("Is it a Weekday or Weekend? \n"+
                                                        "[1] Weekday\n"+
                                                        "[2] Weekend\n");

                                    day  = kb.nextInt();
                                }
                                System.out.println("\n");
                                if (day == 1){
                                    rs = new alternationRate("weekday"); 
                                }
                                else{
                                    rs = new alternationRate("weekend");     
                                    }
                            }break;
                        }
                    }break;
                    default:
                        System.out.println("Wrong input: Try again!");
                } 
            }
	}
}
