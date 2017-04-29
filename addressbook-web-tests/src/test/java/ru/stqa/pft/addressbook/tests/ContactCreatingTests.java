package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactCreatingTests extends TestBase {

    
    @Test
    public void ContactCreatingTests() {
        app.getNavigationHelper().gotoAddNewPage();
//        app.getContactHelper().initContactGreation();
        app.getContactHelper().fillContactForm(new ContactData("Иван", "Иванов", "123456", "sobaka@mail.com", "Пушкина"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();

    }
}
