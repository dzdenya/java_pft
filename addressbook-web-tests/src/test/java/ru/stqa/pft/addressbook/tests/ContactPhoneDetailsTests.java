package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().initContactGreation();
      app.contact().create(new ContactData()
                      .withFirstname("Иван")
                      .withLastname("Иванов")
                      .withHomePhone("+7(800)")
                      .withMobilePhone("33-33-33")
                      .withWorkPhone("22 22 22")
                      .withEmail("sobaka@mail.com")
                      .withAddress("Пушкина 19")
//                      .withGroup("test1")
              , true);
    }
  }

  @Test
  public void testContactInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData infoContactDetails = app.contact().infoFromDetailsForm(contact);
    ContactData infoFromEditFormForDetails = app.contact().infoFromEditFormForDetails(contact);


    assertThat(details(infoContactDetails), equalTo(mergePhones(infoFromEditFormForDetails)));
  }


  private String details (ContactData contact) {
    String result =  Arrays.asList(contact.getAllDetails())
            .stream().filter(s -> !(s == null || s.equals("")))
            .map(ContactPhoneDetailsTests::cleanedForDetail)
            .collect(Collectors.joining(" "));
    System.out.println("Детали: " + result);
    return result;
  }
  private String mergePhones(ContactData contact) {
    String result =  Arrays.asList(
            contact.getFirstname(),
            contact.getLastname(),
            contact.getAddress(),
            contact.getHomePhone(),
            contact.getMobilePhone(),
            contact.getWorkPhone(),
            contact.getEmail(),
            contact.getEmail2(),
            contact.getEmail3())
            .stream().filter(s -> !(s == null || s.equals("")))
            .map(ContactPhoneDetailsTests::cleaned)
            .collect(Collectors.joining(" "));
    System.out.println("Редактирование: " + result);
    return result;
  }

  public static String cleanedForDetail(String phone) {
    return phone.replaceAll("\n", " ")
            .replaceAll("  ", " ")
            .replaceAll(": ", ":");
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\n", "");
  }
}

