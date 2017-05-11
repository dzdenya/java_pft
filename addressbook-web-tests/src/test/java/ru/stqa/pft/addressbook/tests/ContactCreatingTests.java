package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreatingTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {

    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.xml")));
    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[] {new ContactData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2]).withEmail(split[3]).withHomePhone(split[4]).withGroup(split[5])});
      line = reader.readLine();
    }
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