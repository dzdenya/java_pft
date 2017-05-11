package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreatingTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withFirstname("name1").withLastname("lastname1").withHomePhone("12345")
            .withMobilePhone("2345").withWorkPhone("345").withEmail("email@mail.com1").withAddress("address1").withGroup("test1")});
    list.add(new Object[]{new ContactData().withFirstname("name2").withLastname("lastname2").withHomePhone("12345")
            .withMobilePhone("2345").withWorkPhone("345").withEmail("email@mail.com2").withAddress("address2").withGroup("test1")});
    list.add(new Object[]{new ContactData().withFirstname("name3").withLastname("lastname3").withHomePhone("12345")
            .withMobilePhone("2345").withWorkPhone("345").withEmail("email@mail.com3").withAddress("address3").withGroup("test1")});
    return list.iterator();
  }

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
  }

  @Test (dataProvider = "validContacts")
  public void ContactCreatingTests(ContactData contact) {
      Contacts before = app.contact().all();
      app.goTo().gotoAddNewPage();
      File photo = new File("src/test/resources/l6m1.png");
//            .withPhoto(photo);
      app.contact().create(contact, true);
      app.goTo().homePage();
      assertThat(app.contact().contactCount(), equalTo(before.size() + 1));
      Contacts after = app.contact().all();

      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
}