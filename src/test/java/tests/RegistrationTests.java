package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RegistrationTests extends AppiumConfig {
    @Test
    public void registrationSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        boolean result = new AuthenticationScreen(driver)
                .fillEmail("krasleo"+i+"@gmail.com")
                .fillPassword("Cristiano7777$!")
                .submitRegistration()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void registrationSuccessModel(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleo"+i+"@gmail.com").password("Cristiano7777$!").build())
                .submitRegistration()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void registrationSuccessModel2(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Assert.assertTrue(new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleo"+i+"@gmail.com").password("Cristiano7777$!").build())
                .submitRegistration()
                .isActivityTitleDisplayed("Contact list"));
    }

    @Test
    public void registrationWrongEmail(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleogmail.com").password("Cristiano7777$!").build())
                .submitRegistrationNegative()
                .isErrorMessageContainsText("must be a well-formed email address");
    }

    @Test
    public void registrationWrongPassword(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleo@gmail.com").password("Cristiano7777").build())
                .submitRegistrationNegative()
                .isErrorMessageContainsText("Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number; ");
    }

    @Test
    public void registrationDuplicateUser(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleo@gmail.com").password("Cristiano7777$!").build())
                .submitRegistrationNegative()
                .isErrorMessageContainsText("User already exists");
    }


    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver).logout();
    }


}
