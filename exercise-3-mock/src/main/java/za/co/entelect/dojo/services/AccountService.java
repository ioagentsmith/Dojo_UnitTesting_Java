package za.co.entelect.dojo.services;

import za.co.entelect.dojo.Card;

public interface AccountService {

    void withdrawMoney(Card aCard, double transactionAmount, double bankChargeAmount);
}

