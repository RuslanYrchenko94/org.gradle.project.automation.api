package petStore.Store;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.BaseTest;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static globalConstants.Constants.*;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class PetStoreStoreTests extends BaseTest {

    @MethodSource("petStore.Store.StoreDataProvider.PetStoreStoreDataProvider#getPetStoreStoreInventory")
    @ParameterizedTest(name = "Returns pet inventories by status")
    public void getPetStoreInventoryTest(String endpoint, Integer statusCode, String jsonSchema) {
        given().spec(specForRequestCTJson)
                .when().get(format("%s%s", URL, endpoint))
                .then()
                .spec(specForResponse)
                .statusCode(statusCode);
    }
    @MethodSource("petStore.Store.StoreDataProvider.PetStoreStoreDataProvider#postPetStoreOrder")
    @ParameterizedTest(name = "Place an order for a pet")
    public void postPetStoreOrderTest(String endpoint, Integer statusCode, String jsonSchema, String body) {
        Response createOrder = given().spec(specForRequestCTJson)
                .body(body)
                .when().post(format("%s%s", URL, endpoint));
        createOrder.then().spec(specForResponse).statusCode(statusCode)
                .body(matchesJsonSchemaInClasspath(jsonSchema));
        if (createOrder.statusCode() == 200) {
            deleteOrderById(orderID, endpoint);
        }
    }
    @MethodSource("petStore.Store.StoreDataProvider.PetStoreStoreDataProvider#getPetStoreOrderById")
    @ParameterizedTest(name = "Find order by ID, status code {1}")
    public void getPetStoreOrderByIDTest(String endpoint, Integer statusCode, Integer orderID, String jsonSchema) {

        if(statusCode.equals(CODE_OK)){
            createOrder(endpoint);
            Response getOrder = given().spec(specForRequestCTJson)
                    .when().get(format("%s%s%s", URL, endpoint, orderID));
            getOrder.then().spec(specForResponse).statusCode(statusCode)
                    .body(matchesJsonSchemaInClasspath(jsonSchema))
                    .body("id", equalTo(orderID));
            deleteOrderById(orderID, endpoint);
        }
        else if (statusCode.equals(404)) {
            Response getPet = given().spec(specForRequestCTJson)
                    .when().get(format("%s%s%s", URL, endpoint, orderID));
            getPet.then().log().all().statusCode(statusCode)
                    .body(matchesJsonSchemaInClasspath(jsonSchema))
                    .body("message", equalTo("Order not found"));
        }
        else {
            Response getPet =given().spec(specForRequestCTJson)
                    .when().get(format("%s%s", URL, endpoint));
            getPet.then().log().all().statusCode(statusCode).contentType(ContentType.XML);
        }
    }
    @MethodSource("petStore.Store.StoreDataProvider.PetStoreStoreDataProvider#deletePetStoreOrder")
    @ParameterizedTest(name = "Delete order by ID, status code {1}")
    public void deleteOrderByIDTest(String endpoint, Integer statusCode, Integer orderId, String jsonSchema) {

        if(statusCode == CODE_OK){
            createOrder(endpoint);
        }
        Response deleteOrder =given().spec(specForRequestCTJson)
                .when().delete(format("%s%s%s", URL, endpoint, orderId));
        if(statusCode.equals(CODE_OK)){
            deleteOrder.then().log().all().statusCode(statusCode)
                    .body(matchesJsonSchemaInClasspath(jsonSchema))
                    .body("message", equalTo(orderId.toString()));}
        else if (statusCode.equals(404)){
            deleteOrder.then().log().all().statusCode(statusCode)
                .body(matchesJsonSchemaInClasspath(jsonSchema))
                .body("message", equalToIgnoringCase("Order not found"));
        }
        else {
            Response getPet =given().spec(specForRequestCTJson)
                    .when().get(format("%s%s", URL, endpoint));
            getPet.then().log().all().statusCode(statusCode).contentType(ContentType.XML);}
    }
    private void deleteOrderById(Integer orderID, String endpoint) {
        given().when().delete(format("%s%s%s", URL, endpoint, orderID));
    }
    private void createOrder(String endpoint) {
        given().spec(specForRequestCTJson).body(PetStoreStoreOrderValidBody)
                .when().post(format("%s%s", URL, endpoint));
    }
}
