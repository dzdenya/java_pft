package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreatingTests extends TestBase {

    
    @Test
    public void ContactCreatingTests() {
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().createContact(new ContactData("Иван", "Иванов", "123456", "sobaka@mail.com", "Пушкина", null), false);
    }
}
