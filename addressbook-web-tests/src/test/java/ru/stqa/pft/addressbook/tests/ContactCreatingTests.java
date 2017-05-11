package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreatingTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
  }

  @Test
  public void ContactCreatingTests() {
    Contacts before = app.contact().all();
    app.goTo().gotoAddNewPage();
    File photo = new File("src/test/resources/l6m1.png");
    ContactData contact = new ContactData()
            .withFirstname("Иван")
            .withLastname("Иванов")
            .withHomePhone("7800")
            .withMobilePhone("333333")
            .withWorkPhone("222222")
            .withEmail("sobaka@mail.com")
            .withAddress("Пушкина 19")
            .withGroup("test1");
//            .withPhoto(photo);
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().contactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
}