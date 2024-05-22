package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;
    @FindBy(xpath = "//*[@content-desc = 'More options']")
    AndroidElement menuOptions;
    @FindBy(xpath = "//*[@text='Logout']")//regular attribute, change from web testing
    AndroidElement logoutBtn;
    @FindBy(xpath = "//*[@content-desc = 'add']")
    AndroidElement plusBtn;//name of element
    @FindBy(xpath="//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;//name of list
    @FindBy(xpath="//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement>contactList;
    @FindBy(id="android:id/button1")
    AndroidElement yesBtn;

    int countBefore;
    int countAfter;


    public boolean isActivityTitleDisplayed(String text){

        //return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView,text,8);
    }

    public AuthenticationScreen logout(){
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm(){
        if (activityTextView.getText().equals("Contact list"))
            plusBtn.click();
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastNme) {
//        List<AndroidElement>list = driver.findElements(By.id(""));
        isShouldHave(activityTextView,"Contact list", 10);
        System.out.println("size of list---> "+contactNameList.size());
        boolean isPresent = false;
        for(AndroidElement element:contactNameList){
            if(element.getText().equals(name+" "+lastNme)){
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

    public  ContactListScreen deleteFirstContact(){
        isActivityTitleDisplayed("Contact list");

        countBefore=contactList.size();
        System.out.println(countBefore);

        AndroidElement first = contactList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX()+rectangle.getWidth()/8;
        int yFrom = rectangle.getY()+rectangle.getHeight()/2;
        //int xTo = rectangle.getX()+(rectangle.getWidth()/8)*7;
        int xTo = rectangle.getWidth() - xFrom;
        int yTo = rectangle.getY()+rectangle.getHeight()/2;

        TouchAction<?>touchAction = new TouchAction<>(driver);//touch action will take the type of driver(android element)
        touchAction.longPress(PointOption.point(xFrom,yFrom))
                .moveTo(PointOption.point(xTo,yTo)).release().perform();

        should(yesBtn,10);
        yesBtn.click();

        pause(3000);
        countAfter=contactList.size();
        System.out.println(countAfter);
        return this;
    }

    public ContactListScreen isListSizeLessInOne() {
        Assert.assertEquals(countBefore-countAfter, 1);
        return  this;
    }
}
