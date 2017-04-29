package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreatingTests extends TestBase {

  @Test
  public void testGroupCreating() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupGreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, "test3"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  }

}
