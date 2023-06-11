package globalConstants;

import java.time.LocalDateTime;

import static dataModels.PetStoreData.*;


public class Constants {
    public static final String database_url = "jdbc:postgresql://localhost:5432/postgres";
    public static final String database_username = "postgres";
    public static final String database_password = "qwerty1!";
    //status codes
    public static final Integer CODE_OK = 200;
    //params
    public static final String STATUS = "status";
    //URLs
    public static final String URL = "https://petstore.swagger.io/v2";

    public static final LocalDateTime datetime = LocalDateTime.now();

    public static final Integer petID = 2023;

    public static final Integer orderID = 1294;
    public static final Integer userID = 1203;
    public static final String Username = "TestUser";
    public static final String PetStorePetBodyWithInvalidID = "{\"id\":0000," +
            "\"category\":{\"id\":64598,\"name\":\"doggie\"}," +
            "\"name\":\"PIKI\"," +
            "\"photoUrls\":[\"http://py.jpg\"]," +
            "\"tags\":[{\"id\":2312,\"name\":\"papy\"}]," +
            "\"status\":\"available\"}";
    public static final String PetStorePetBodyWithInvalidBody = "{\"id\":2023," +
            "\"category\":{\"id\":64598,\"name\":\"doggie\"}," +
            "\"name\":\"PIKI\"," +
            "\"photoUrls\":[\"http://py.jpg\"]," +
            "\"tags\":[[{\"id\":2312,\"name\":\"papy\"}]," +
            "\"status\":\"available\"}";
    public static final String PetStorePetValidBody = postCreatePet(petID).toString();


    public static final String PetStoreStoreOrderValidBody = postCreateOrder(orderID).toString();
    public static final String PetStoreStoreOrderWithInvalidBody = "PetStoreOrderModel{petId = '777'," +
            "quantity = '5'," +
            "id = '1294'," +
            "shipDate = '2023-05-15T19:14:43.669751600'," +
            "complete = 'true'," +
            "status = 'placed'}";
    public static final String PetStoreUserValidBody = postCreateUser(userID, Username).toString();
    public static final String PetStoreUserWithInvalidBody ="{\"id\":," +
            "\"username\":," +
            "\"firstName\":\"\"," +
            "\"lastName\":\"\"," +
            "\"email\":\"\"," +
            "\"password\":\"\"," +
            "\"phone\":\"+\"," +
            "\"userStatus\":}";
}
