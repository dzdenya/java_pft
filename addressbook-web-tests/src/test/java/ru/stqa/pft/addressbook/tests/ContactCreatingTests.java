package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
        app.goTo().gotoAddNewPage();
        ContactData contact = new ContactData().withFirstname("Иван").withLastname("Иванов").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
