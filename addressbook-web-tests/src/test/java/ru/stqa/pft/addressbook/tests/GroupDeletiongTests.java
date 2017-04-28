package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletiongTests extends TestBase{


    @Test
    public void testGroupDeletiong() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
