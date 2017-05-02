package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by Denys on 4/29/2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().initContactGreation();
      app.contact().create(new ContactData()
                      .withFirstname("Иван")
                      .withLastname("Иванов")
                      .withMobile("123456")
                      .withEmail("sobaka@mail.com")
                      .withAddress("Пушкина").withGroup("test1")
              , true);
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(deleteContact.getId())
            .withFirstname("Иван")
            .withLastname("Иванов");
    app.contact().modifyContact(contact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(deleteContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
