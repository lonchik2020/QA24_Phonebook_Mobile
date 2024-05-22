package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginTestsSecond extends AppiumConfig {

    @Test
    public void loginSuccess(){
        new AuthenticationScreen(driver)
                .fillEmail("krasleo@gmail.com")
                .fillPassword("Cristiano7777$!")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginSuccessModel(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleo@gmail.com").password("Cristiano7777$!").build())
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginWrongEmail(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleogmail.com").password("Cristiano7777$!").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }


}
