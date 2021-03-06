package za.co.entelect.dojo.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.entelect.dojo.domain.Card;
import za.co.entelect.dojo.enums.CardValidationErrorType;
import za.co.entelect.dojo.exceptions.AccountException;
import za.co.entelect.dojo.exceptions.ValidationException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceImplTest {

    @Mock
    private Track2DataValidationService track2DataValidationService;

    @Mock
    private PinService pinService;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private CardService cardService = new CardServiceImpl();

    @Before
    public void setUp() {
        resetAll();
    }

    @Test
    public void testWithdrawMoneySuccess() {
        /*doNothing().when(track2DataValidationService).isValid(anyString());
        doNothing().when(pinService).validatePin(anyString(), anyString());
        doNothing().when(accountService).withdrawMoney(any(Card.class), anyDouble(), anyDouble());*/
        Card card = getCard(true, 100L);
        cardService.withdrawMoney(card, 200L);

        verify(track2DataValidationService).isValid(anyString());
        verify(pinService).validatePin(anyString(), anyString());
        verify(accountService).withdrawMoney(eq(card), anyLong(), anyLong());
    }

    @Test
    public void testInsufficientFunds() {
        when(track2DataValidationService.isValid(anyString())).thenReturn(true);
        doNothing().when(pinService).validatePin(anyString(), anyString());
        doThrow(new AccountException(CardValidationErrorType.INSUFFICIENT_FUNDS)).when(accountService).withdrawMoney(any(Card.class), anyLong(), anyLong());
        try {
            cardService.withdrawMoney(getCard(true,100L), 200L);
        } catch (AccountException e) {
            Assert.assertEquals(CardValidationErrorType.INSUFFICIENT_FUNDS, e.getCardValidationErrorType());
        }
    }

    @Test(expected = ValidationException.class)
    public void testInvalidPin() {
        when(track2DataValidationService.isValid(anyString())).thenReturn(true);
        doNothing().when(pinService).validatePin(anyString(), anyString());
        doThrow(new ValidationException(CardValidationErrorType.INVALID_PIN)).when(pinService).validatePin(anyString(),anyString());
        cardService.withdrawMoney(getCard(true,100L), 200L);
    }

    @Test
    public void testBankCharge() {
        when(track2DataValidationService.isValid(anyString())).thenReturn(true);
        doNothing().when(pinService).validatePin(anyString(), anyString());
        ArgumentCaptor<Card> argument = ArgumentCaptor.forClass(Card.class);
        Card card = getCard(true,100L);
        cardService.withdrawMoney(card, 200L);
        verify(accountService).withdrawMoney(argument.capture(), anyLong(), anyLong());
        Assert.assertEquals(argument.getValue().getTrack2(), card.getTrack2());
    }

    private void resetAll() {
        reset(track2DataValidationService, pinService, accountService);
    }

    private Card getCard(boolean validCardNumber, long balanceAvailable) {
        String cardNumber = "4929900481403641";
        if (!validCardNumber) {
            cardNumber = "4929900481403642";
        }
        String track2 = cardNumber + "1703101";
        String pinBlock = "FF" + cardNumber;
        return new Card(track2,pinBlock, balanceAvailable);
    }
}