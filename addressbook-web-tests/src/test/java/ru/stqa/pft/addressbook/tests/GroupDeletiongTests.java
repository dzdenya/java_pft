package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletiongTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletiong() {
    Groups before = app.db().groups();
    GroupData deleteGroup = before.iterator().next();
    app.group().delete(deleteGroup);
    assertThat(app.group().groupCount(), equalTo(before.size() - 1));
    Groups after = app.db().groups();

    assertThat(after, equalTo(before.without(deleteGroup)));
    verifyGroupListInUI();
  }
}
