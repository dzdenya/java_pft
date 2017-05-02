package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Denys on 4/29/2017.
 */
public class ContactDeletiongTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0){
      app.contact().initContactGreation();
      app.contact().create(new ContactData()
              .withFirstname("Иван")
              .withLastname("Иванов")
              .withGroup("test1")
              , true);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    app.contact().deleteContact(before);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before,after);
  }

}
