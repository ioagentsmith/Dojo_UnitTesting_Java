package za.co.entelect.dojo.util;

import org.apache.commons.lang3.StringUtils;

public class ValidationUtils {

    private static final int SERVICE_CODE_LENGTH = 3;
    private static final int CREDIT_CARD_LENGTH = 16;
    public static final int DEC = 12;
    public static final int JAN = 1;
    public static final int EXPIRTY_DATE_LENGTH = 4;

    static int[][] serviceCodeValues = new int[][]{
            {1,2,5,6,7,9},
            {0,2,4},
            {0,1,2,3,4,5,6,7}
    };


    public static boolean isValidCardNumber(String ccNumber) {
        if (StringUtils.length(ccNumber) != CREDIT_CARD_LENGTH) {
            return false;
        }

        if(!isNumeric(ccNumber)){
            return false;
        }

        int sum = 0;
        boolean alternate = false;

        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate) {
                n = n*2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public static boolean isValidServiceCode(String serviceCode) {
        if (serviceCode.length() != SERVICE_CODE_LENGTH) {
            return false;
        }

        if(!isNumeric(serviceCode)){
            return false;
        }

        for(int i = 0; i < SERVICE_CODE_LENGTH; i++) {
            int n = Integer.parseInt(serviceCode.substring(i,i+1));
            int[] validValues = serviceCodeValues[i];
            boolean needle = false;
            for (int k = 0; k < validValues.length; k++) {
                if (n == validValues[k]) {
                    needle = true;
                }
            }
            if (!needle) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidExpirationDate(String dateString) {
        if(StringUtils.length(dateString) != EXPIRTY_DATE_LENGTH){
            return false;
        }

        if (!isNumeric(dateString)) {
            return false;
        }

        Integer month = Integer.valueOf(dateString.substring(2, 4));

        return month >= JAN && month <= DEC;
    }

    private static boolean isNumeric(String check) {
        for (char aChar : check.toCharArray()) {
            if(!Character.isDigit(aChar)){
                return false;
            }
        }
        return true;
    }
}
