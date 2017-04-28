package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletiongTests extends TestBase{


    @Test
    public void testGroupDeletiong() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }

}
