package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Denys on 4/29/2017.
 */
public class ContactModificationTests extends TestBase {


  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Иван", "Иванов", "123456", "sobaka@mail.com", "Пушкина"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

  }
}
