package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by Denys on 4/29/2017.
 */
public class ContactDeletiongTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0){
      app.contact().initContactGreation();
      app.contact().create(new ContactData()
              .withFirstname("Иван")
              .withLastname("Иванов")
              .withGroup("test1")
              , true);
    }
  }

  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    app.contact().deleteContact(deleteContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deleteContact);
    Assert.assertEquals(before,after);
  }

}
