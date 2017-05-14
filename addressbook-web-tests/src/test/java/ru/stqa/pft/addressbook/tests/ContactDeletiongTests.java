package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Denys on 4/29/2017.
 */
public class ContactDeletiongTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().initContactGreation();
      app.contact().create(new ContactData()
                      .withFirstname("Иван")
                      .withLastname("Иванов")
                      .withHomePhone("7800")
                      .withMobilePhone("333333")
                      .withWorkPhone("222222")
                      .withEmail("sobaka@mail.com")
                      .withAddress("Пушкина 19")
                      .withGroup("test1")
              , true);
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deleteContact = before.iterator().next();
    app.contact().deleteContact(deleteContact);
    assertThat(app.contact().contactCount(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(deleteContact)));
  }
}
