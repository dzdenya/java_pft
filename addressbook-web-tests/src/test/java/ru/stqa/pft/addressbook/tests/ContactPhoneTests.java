package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoEditForm)) );
    assertThat(contact.getAddress(), equalTo(contactInfoEditForm.getAddress()) );
    assertThat(contact.getEmail(), equalTo(contactInfoEditForm.getEmail()) );
  }

  /*@Test
  public void testAddressModification() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoEditForm.getAddress()) );
  }

  @Test
  public void testEmailModification() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getEmail(), equalTo(contactInfoEditForm.getEmail()) );
  }*/

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
