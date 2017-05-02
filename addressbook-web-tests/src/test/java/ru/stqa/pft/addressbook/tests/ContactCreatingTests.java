package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreatingTests extends TestBase {

    
    @Test
    public void ContactCreatingTests() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroupForContact()) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        app.goTo().gotoAddNewPage();
        ContactData contact = new ContactData("Иван", "Иванов", null, null, null, "test1");
        app.contact().create(contact, true);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
