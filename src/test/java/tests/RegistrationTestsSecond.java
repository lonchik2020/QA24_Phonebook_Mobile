package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class RegistrationTestsSecond extends AppiumConfig {

    @Test
    public void registrationSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        new AuthenticationScreen(driver)
                .fillEmail("krasleo"+i+"@gmail.com")
                .fillPassword("Cristiano7777$!")
                .submitRegistration()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void registrationSuccessModel() {
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleo"+i+"@gmail.com").password("Cristiano7777$!").build())
                .submitRegistration()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void registrationDuplicateUser(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleo@gmail.com").password("Cristiano7777$!").build())
                .submitRegistrationNegative()
                .isErrorMessageContainsText("User already exists");
    }
}
