package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Denys on 10-May-17.
 */
public class ContactPhoneInfoTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().initContactGreation();
      app.contact().create(new ContactData()
                      .withFirstname("Иван")
                      .withLastname("Иванов")
                      .withHomePhone("+7(800)")
                      .withMobilePhone("33-33-33")
                      .withWorkPhone("22 22 22")
                      .withEmail("sobaka@mail.com")
                      .withAddress("Пушкина 19")
                      .withGroup("test1")
              , true);
    }
  }

  @Test (enabled = false)
  public void testContactInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoContactDetails = app.contact().infoContactDetails(contact);

    assertThat(contact.getAllDedails(), equalTo(mergeDetails(contactInfoContactDetails)) );

  }


  private String mergeDetails(ContactData contact) {
    return Arrays.asList(
            contact.getFirstname(),
            contact.getLastname(),
            contact.getAddress(),
            contact.getEmail(),
            contact.getHomePhone(),
            contact.getMobilePhone(),
            contact.getWorkPhone())
            .stream().filter(s -> !(s==null || s.equals("")))
            .map(ContactPhoneInfoTests::cleaned)
            .collect(Collectors .joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}

