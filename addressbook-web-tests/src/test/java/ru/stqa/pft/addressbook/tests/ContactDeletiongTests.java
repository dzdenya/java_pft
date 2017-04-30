package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

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
//    app.getContactHelper().selectContact();
//    int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().closeAlert();
    app.getContactHelper().returnToHomePageAfterDeletiong();
//    int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

//
//    before.remove(before.size() - 1);
//    Assert.assertEquals(before,after);
  }
}
