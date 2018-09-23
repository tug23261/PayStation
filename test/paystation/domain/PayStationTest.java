/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ishan
 */
public class PayStationTest {
    
    public PayStationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPayment method, of class PayStation.
     */
    @Test
    public void testAddPayment() throws Exception {
        System.out.println("addPayment");
        int coinValue = 0;
        PayStation instance = new PayStationImpl();
        instance.addPayment(coinValue);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of readDisplay method, of class PayStation.
     */
    @Test
    public void testReadDisplay() {
        System.out.println("readDisplay");
        PayStation instance = new PayStationImpl();
        int expResult = 0;
        int result = instance.readDisplay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of buy method, of class PayStation.
     */
    @Test
    public void testBuy() {
        System.out.println("buy");
        PayStation instance = new PayStationImpl();
        Receipt expResult = null;
        Receipt result = instance.buy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of cancel method, of class PayStation.
     */
    @Test
    public void testCancel() {
        System.out.println("cancel");
        PayStation instance = new PayStationImpl();
        Map<Integer, Integer> expResult = null;
        Map<Integer, Integer> result = instance.cancel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of empty method, of class PayStation.
     */
    @Test
    public void testEmpty() {
        System.out.println("empty");
        PayStation instance = new PayStationImpl();
        int expResult = 0;
        int result = instance.empty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    public class PayStationImpl implements PayStation {

        public void addPayment(int coinValue) throws IllegalCoinException {
        }

        public int readDisplay() {
            return 0;
        }

        public Receipt buy() {
            return null;
        }

        public Map<Integer, Integer> cancel() {
            return null;
        }

        public int empty() {
            return 0;
        }
    }
    
}
