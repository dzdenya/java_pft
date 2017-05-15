package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Denys on 4/29/2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().initContactGreation();
      app.contact().create(new ContactData()
                      .withFirstname("Иван")
                      .withLastname("Иванов")
                      .withHomePhone("+7(800)")
                      .withMobilePhone("")
                      .withWorkPhone("22 22 22")
                      .withEmail("sobaka@mail.com")
                      .withAddress("Пушкина 444")
                      .withGroup("test1")
              , true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifyContact = before.iterator().next();
    File photo = new File("src/test/resources/l6m1.png");
    ContactData contact = new ContactData()
            .withId(modifyContact.getId())
            .withFirstname("Иван")
            .withLastname("Иванов")
            .withHomePhone("+7(800)")
            .withMobilePhone("33-33-33")
            .withEmail("mail@mail.com")
            .withAddress("Ленина 19")
            .withWorkPhone("22 22 22")
            .withPhoto(photo);
    app.contact().modifyContact(contact);
    assertThat(app.contact().contactCount(), equalTo(before.size()));

    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    verifyContactListInUI();
  }


}
