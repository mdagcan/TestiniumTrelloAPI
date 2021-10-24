package com.trelloAPI.tests;

import org.testng.annotations.Test;

public class TrelloAPITestFull_Method extends MethodPage {

    @Test
    public void test1() {
        createBoard();
        createCards();
        updateRandomCard();
        deleteCards();
        deleteBoard();
    }
}
