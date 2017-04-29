package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Denys on 4/29/2017.
 */
public class ContactDeletiongTests extends TestBase{

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().initContactGreation();
      app.getContactHelper().createContact(new ContactData("Иван", "Иванов", "123456", "sobaka@mail.com", "Пушкина", null), false);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().closeAlert();
    app.getContactHelper().returnToHomePageAfterDeletiong();

  }
}
