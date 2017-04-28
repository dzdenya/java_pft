package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreatingTests extends TestBase {

  @Test
  public void testGroupCreating() {
    gotoGroupPage();
    initGroupGreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
