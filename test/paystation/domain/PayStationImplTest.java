/**
 * Test cases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package paystation.domain;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import static paystation.domain.PayStationImpl.rs;

public class PayStationImplTest {

    PayStationImpl ps;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setup() {
        ps = new PayStationImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void linearShouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        rs = new linearRate();
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents with linear rate strategy",
                2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void linearShouldDisplay10MinFor25Cents() throws IllegalCoinException {
        rs = new linearRate();
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void linearShouldDisplay14MinFor10And25Cents()
            throws IllegalCoinException {
        rs = new linearRate();
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Should display 14 min for 10+25 cents",
                14, ps.readDisplay());
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldReturnCorrectReceiptWhenBuy()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull("Receipt reference cannot be null",
                receipt);
        assertEquals("Receipt value must be 16 min.",
                16, receipt.value());
    }

    /**
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void linearShouldReturnReceipt40WhenBuy100c()
            throws IllegalCoinException {
        rs = new linearRate();
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy()
            throws IllegalCoinException {
        rs = new linearRate();
        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        // verify that the display reads 0
        assertEquals("Display should have been cleared",
                0, ps.readDisplay());
        // verify that a following buy scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Next add payment should display correct time",
                14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals("Next buy should return valid receipt",
                14, r.value());
        assertEquals("Again, display should be cleared",
                0, ps.readDisplay());
    }

    /**
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel()
            throws IllegalCoinException {
        rs = new linearRate();
        ps.addPayment(10);
        ps.cancel();
        assertEquals("Cancel should clear display",
                0, ps.readDisplay());
        ps.addPayment(25);
        assertEquals("Insert after cancel should work",
                10, ps.readDisplay());
    }
    
     /**
     * Verify that empty returns correct amount inserts
     */
    @Test
    public void shouldReturnDepositOnlyAfterBuy()throws IllegalCoinException{
        rs = new linearRate();
        ps.addPayment(5);
        ps.addPayment(25);
        //optional
        ps.cancel();

        //start new payment
        ps.addPayment(10);

        ps.buy();

        ps.addPayment(25);
        ps.addPayment(5);
        ps.buy();
        assertEquals("Nothing returned",40, ps.empty());
    }
     @Test
    public void shouldAddToAmountReturnByEmpty()throws IllegalCoinException{
        rs = new linearRate();
        ps.addPayment(10);
        ps.buy();
        ps.empty();
      
        HashMap<Integer,Integer> testMap = new HashMap<>(ps.cancel());
        assertFalse(testMap.containsKey(25));

    }

    @Test
    public void shouldReturnTotalToZeroAfterEmpty()throws IllegalCoinException{
        rs = new linearRate();
        ps.addPayment(25);
        ps.buy();
        ps.empty();
        assertEquals(0,ps.readDisplay());

    }

    @Test
    public void progressiveShouldDisplay130MinFor400Cents() throws IllegalCoinException 
    {
        rs = new progressiveRate();
        for (int i = 0; i < 16;i++)
            ps.addPayment(25); // add 16 quarters
        assertEquals("Progressive should buy 130 minutes with 400 cents",
        ps.readDisplay(), 130);
    }
    
    @Test
    public void progressiveShouldDisplay75MinFor200Cents() throws IllegalCoinException 
    {
        rs = new progressiveRate();
        for (int i = 0; i < 8;i++)
            ps.addPayment(25); // add 8 quarters
        assertEquals("Progressive should buy 75 minutes with 200 cents",
        ps.readDisplay(), 75);
    }
    
    @Test
    public void alternatingShouldUseLinearOnWeekends() throws IllegalCoinException 
    {
        rs = new alternationRate("weekend");
        for (int i = 0; i < 8;i++)
            ps.addPayment(25); // add 8 quarters
        assertEquals("Weekend Alternating should buy 80 minutes with 200 cents",
        ps.readDisplay(), 80);
    }
        @Test
    public void alternatingShouldUseProgOnWeekdays() throws IllegalCoinException 
    {
        rs = new alternationRate("weekday");
        for (int i = 0; i < 8;i++)
            ps.addPayment(25); // add 8 quarters
        assertEquals("Weekday Alternating should buy 75 minutes with 200 cents",
        ps.readDisplay(), 75);
    }
    
    @Test
    public void shouldReturnOneCoinEntered() throws IllegalCoinException{
        //entering one coin to machine
        rs = new linearRate();
        ps.addPayment(25);

        //creating a reference to the map in paystation object to access values
        HashMap<Integer,Integer> testMap = new HashMap<>(ps.cancel());
        //will test that map contains key for 25  
        assertTrue(testMap.containsKey(25));

    }

    @Test
    public void shouldReturnMultipleCoins() throws IllegalCoinException{
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(10);
        
        HashMap<Integer,Integer> testMap = new HashMap<>(ps.cancel());
        assertTrue("Should return the exact coins you put in",testMap.containsKey(5) && testMap.get(5) == 1
            && testMap.containsKey(10) && testMap.get(10) == 2);
    }


    @Test
    public void shouldEmptyMapAfterCancel() throws IllegalCoinException{
        ps.addPayment(5);
        ps.addPayment(10);
        //calling cancel before the reference map is mad should leave that map as empty
        //as cancel clears the existing map in the paystation object
        ps.cancel();
        HashMap<Integer,Integer> testMap = new HashMap<>(ps.cancel());
        //the isEmpty method returns true if map is empty test will pass in the map is empty after cancel
        assertTrue(testMap.isEmpty());
    }

    @Test
    public void shouldEmptyMapAfterBuy() throws IllegalCoinException{
        ps.addPayment(25);
        ps.addPayment(10);
        ps.buy();
        HashMap<Integer,Integer> testMap = new HashMap<>(ps.cancel());
        //same as above should be empty as the buy calls reset which should clear the map
        assertTrue(testMap.isEmpty());
    }

    /**
    /**
     * Test of addPayment method, of class PayStationImpl.
     
    @Test
    public void testAddPayment() throws Exception {
        System.out.println("addPayment");
        ps.addPayment(5);
    }

    /**
     * Test of readDisplay method, of class PayStationImpl.
     
    @Test
    public void testReadDisplay() {
        System.out.println("readDisplay");
        PayStationImpl instance = new PayStationImpl();
        int expResult = 0;
        int result = instance.readDisplay();
        assertEquals(expResult, result);
    }

    /**
     * Test of buy method, of class PayStationImpl.
    
    @Test
    public void testBuy() {
        System.out.println("buy");
        PayStationImpl instance = new PayStationImpl();
        Receipt expResult = null;
        Receipt result = instance.buy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    } */

    /**
     * Test of cancel method, of class PayStationImpl.
    @Test
    public void testCancel() {
        System.out.println("cancel");
        PayStationImpl instance = new PayStationImpl();
        Map<Integer, Integer> expResult = null;
        Map<Integer, Integer> result = instance.cancel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }     
     */

    /**
     * Test of empty method, of class PayStationImpl.
     */
    @Test
    public void testEmpty() {
        int result = ps.empty();
        assertEquals("empty", 0, result);
    }
}
