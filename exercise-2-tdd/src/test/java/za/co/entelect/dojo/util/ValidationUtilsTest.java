package za.co.entelect.dojo.util;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationUtilsTest {

    @Test
    public void testInvalidCardNumber(){
        assertFalse(ValidationUtils.isValidCardNumber("5454"));
        assertFalse(ValidationUtils.isValidCardNumber("7987954151321654654844984984"));
        assertFalse(ValidationUtils.isValidCardNumber("NOT_A_NUMBER__:<"));
        assertFalse(ValidationUtils.isValidCardNumber("4498484616416544"));
    }

    @Test
    public void testValidCardNumber(){
       assertTrue(ValidationUtils.isValidCardNumber("4388576019611030"));
       assertTrue(ValidationUtils.isValidCardNumber("4388576019611022"));
       assertTrue(ValidationUtils.isValidCardNumber("5388576018900076"));
       assertTrue(ValidationUtils.isValidCardNumber("5388576018900084"));
    }

    @Test
    public void testInvalidServiceCode(){
        assertFalse(ValidationUtils.isValidServiceCode("1"));
        assertFalse(ValidationUtils.isValidServiceCode("1234"));
        assertFalse(ValidationUtils.isValidServiceCode("AAA"));
        assertFalse(ValidationUtils.isValidServiceCode("589"));
        assertFalse(ValidationUtils.isValidServiceCode("228"));
    }

    @Test
    public void testValidServiceCode(){
        assertTrue(ValidationUtils.isValidServiceCode("947"));
        assertTrue(ValidationUtils.isValidServiceCode("542"));
        assertTrue(ValidationUtils.isValidServiceCode("100"));
    }

}
