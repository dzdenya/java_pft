package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreatingTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroupForContact()) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
    }

    @Test
    public void ContactCreatingTests() {
        Contacts before = app.contact().all();
        app.goTo().gotoAddNewPage();
        ContactData contact = new ContactData().withFirstname("Иван").withLastname("Иванов").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
}
