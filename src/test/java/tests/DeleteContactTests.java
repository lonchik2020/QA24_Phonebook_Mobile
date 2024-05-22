package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DeleteContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleo@gmail.com")
                        .password("Cristiano7777$!").build())
                .submitLogin();
    }

    @Test
    public void deleteFirstContact(){
        new ContactListScreen(driver)
                .deleteFirstContact()
                .isListSizeLessInOne();
    }

    @Test
    public void deleteAllContactSuccess(){
        new ContactListScreen(driver)
                .removeAllContacts()
                .isNoContactsHere();
    }

}
