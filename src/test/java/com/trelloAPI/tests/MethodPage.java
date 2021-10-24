package com.trelloAPI.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;
public class MethodPage {
        String key = "Your_KEY";        // KEY BİLGİNİZİ BURAYA GİRİNİZ
        String token = "Your_TOKEN";    // TOKEN BİLGİNİZİ BURAYA GİRİNİZ
        String baseTrelloURI = "https://api.trello.com/1";
        String boardName = "myRestAssuredBoard";
        String boardId, IDlist, card_1ID, card_2ID;
        String cardName_1 = "Card One";
        String cardName_2 = "Card Two";

    public void createBoard() {

        String baseURI = baseTrelloURI + "/boards?token=" + token + "&key=" + key +"&name=" + boardName; // yeni boardoluşturmak için URI

        Response response = given().contentType(ContentType.JSON).when().post(baseURI); // yeni board oluşturulur

        boardId = response.body().jsonPath().getString("id"); // yeni oluşturulan board bilgileri alınır
        IDlist = response.body().jsonPath().getString("idList");

    }
    public void createCards() {

        String createCard_1URI = baseTrelloURI + "/cards?token=" + token + "&key=" + key + "&idList=" + IDlist + "&name=" + cardName_1; // card oluşturmak için URI
        String createCard_2URI = baseTrelloURI + "/cards?token=" + token + "&key=" + key + "&idList=" + IDlist + "&name=" + cardName_2;

        Response response = given().contentType(ContentType.JSON).when().post(createCard_1URI); // birinci card oluşturulur
        card_1ID = response.body().jsonPath().getString("id");                             // birinci card bilgileri alınır
        response = given().contentType(ContentType.JSON).when().post(createCard_2URI);
        card_2ID = response.body().jsonPath().getString("id");

    }
    public void updateRandomCard() {

        String[] cardIDs = {card_1ID, card_2ID};        // oluşturulan iki card dan rasgele seçmek için
        Random random = new Random();                   // random methodu kullanılır
        int randomNumber = random.nextInt(2);
        String randomCardID = cardIDs[randomNumber];

        String randomCardUpdateURI = baseTrelloURI + "/cards/" + randomCardID + "?token=" + token + "&key=" + key + "&name=UpdatedCardName"; // ad güncellemek için URI

        Response response = given().contentType(ContentType.JSON).when().put(randomCardUpdateURI); // rasgele seçilen card adı güncellenir

    }
    public void deleteCards() {

        String deleteCard_1URI = baseTrelloURI + "/cards/" + card_1ID + "?token=" + token + "&key=" + key; // card silmek için oluşturulan URI
        String deleteCard_2URI = baseTrelloURI + "/cards/" + card_2ID + "?token=" + token + "&key=" + key;

        Response response = given().contentType(ContentType.JSON).when().delete(deleteCard_1URI); // card silme
        response = given().contentType(ContentType.JSON).when().delete(deleteCard_2URI);

    }
    public void deleteBoard() {

        String deleteBoardURI = baseTrelloURI + "/boards/" + boardId + "?token=" + token + "&key=" + key; // board silmek için oluşturulan URI

        Response response = given().contentType(ContentType.JSON).when().delete(deleteBoardURI);  // board silme

    }
}
