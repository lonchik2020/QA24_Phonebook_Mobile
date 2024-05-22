package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("krasleo@gmail.com").password("Cristiano7777$!")
                        .build())
                .submitLogin();
    }

    @Test
    public void createNewContactSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastNme("Wow"+i)
                .email("wow"+i+"@gmail.com")
                .phone("67676797"+i)
                .address("London")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastNme());

    }

    @Test
    public void createNewContactSuccessReq(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastNme("Wow"+i)
                .email("wow"+i+"@gmail.com")
                .phone("67676797"+i)
                .address("London")
                .description("Amigo")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastNme());
    }

    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()
                .lastNme("Dow")
                .email("dow@gmail.com")
                .phone("676767978822")
                .address("London")
                .description("Empty name")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver).logout();
    }

}
