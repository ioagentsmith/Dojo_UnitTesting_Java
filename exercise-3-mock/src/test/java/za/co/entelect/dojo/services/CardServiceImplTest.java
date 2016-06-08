package za.co.entelect.dojo.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.entelect.dojo.Card;
import za.co.entelect.dojo.exceptions.AccountException;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceImplTest {

    //add mocks here

    //inject the mocks here


    @Before
    public void setUp() {
        resetAll();
    }

    @Test
    public void testWithdrawMoneySuccess() {
        // void methods in services to do NOTHING
    }

    @Test
    public void testInsufficientFunds() {
        /**
         * This test should assert a certain exception to be thrown with
         * the correct value where the exception has a value you gave with the mock
         */

        try {

        } catch (AccountException e) {

        }
    }

    @Test
    public void testInvalidPin() {
        //merely expect a certain exception
    }

    @Test
    public void testBankCharge() {
        /**
         * Using ArgumentCaptor, verify that the track2 data sent to the account service was the track2 generated by us here.
         */
    }

    private void resetAll() {
        //call reset for all mocks
    }

    private Card getCard(boolean validCardNumber, double balanceAvailable) {
        String cardNumber = "4929900481403641";
        if (!validCardNumber) {
            cardNumber = "4929900481403642";
        }
        String track2 = cardNumber + "1703101";
        String pinBlock = "FF" + cardNumber;
        return new Card(track2,pinBlock, balanceAvailable);
    }
}