package petStore.Pet;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.BaseTest;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static globalConstants.Constants.*;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;


public class PetStorePetTests extends BaseTest {

    @MethodSource("petStore.Pet.PetDataProvider.PetStorePetDataProvider#getPetStorePetsByStatus")
    @ParameterizedTest(name = "Finds Pets by status {3}")
    public void getPetStorePetsByStatusTest(String endpoint, Integer statusCode, String paramKey, String paramValue, String bodyKey) {
        given().spec(specForRequestCTJson)
                .param(paramKey, paramValue)
                .when().get(format("%s%s", URL, endpoint))
                .then()
                .spec(specForResponse)
                .statusCode(statusCode)
                .body(bodyKey, is(notNullValue()));
    }
    @MethodSource("petStore.Pet.PetDataProvider.PetStorePetDataProvider#postPetStorePet")
    @ParameterizedTest(name = "Updates a pet in the store, status code {1}")
    public void postPetStorePetTest(String endpoint, Integer statusCode, Integer petID, String jsonSchema, String body) {

        Response createPet = given().spec(specForRequestCTJson)
                .body(body)
                .when().post(format("%s%s", URL, endpoint));
        createPet.then().spec(specForResponse).statusCode(statusCode)
                .body(matchesJsonSchemaInClasspath(jsonSchema));
        if(createPet.statusCode() == CODE_OK){
            deletePetById(petID, endpoint);
        }
    }
    @MethodSource("petStore.Pet.PetDataProvider.PetStorePetDataProvider#postPetStorePetById")
    @ParameterizedTest(name = "Updates a pet in the store by id, status code {1}")
    public void postPetStorePetByIDTest(String endpoint, Integer statusCode, Integer petID, String jsonSchema, String body) {
        if(statusCode.equals(CODE_OK)){
            createPet(endpoint);
        }
        Response updatePetById =given().spec(specForRequestCTFormData)
                .body(body)
                .when().post(format("%s%s%s", URL, endpoint, petID));
        updatePetById.then().spec(specForResponse).statusCode(statusCode)
                .body(matchesJsonSchemaInClasspath(jsonSchema));
        deletePetById(petID, endpoint);

    }
    @MethodSource("petStore.Pet.PetDataProvider.PetStorePetDataProvider#deletePetStorePetById")
    @ParameterizedTest(name = "Deletes a pet by id, status code {1}")
    public void deletePetByIDTest(String endpoint, Integer statusCode, Integer petID, String jsonSchema) {

       if(statusCode.equals(CODE_OK)){
            createPet(endpoint);
       }
       Response deletePet =given().spec(specForRequestCTJson)
                .when().delete(format("%s%s%s", URL, endpoint, petID));
       if(statusCode.equals(CODE_OK)){
       deletePet.then().log().all().statusCode(statusCode)
                .body(matchesJsonSchemaInClasspath(jsonSchema))
                .body("message", equalTo(petID.toString()));}
       else {deletePet.then().log().all().statusCode(statusCode);
       }
    }
    @MethodSource("petStore.Pet.PetDataProvider.PetStorePetDataProvider#getPetStorePetById")
    @ParameterizedTest(name = "Find pet by id, status code {1}")
    public void getPetByIDTest(String endpoint, Integer statusCode, Integer petID, String jsonSchema) {

        if(statusCode.equals(CODE_OK)){
            createPet(endpoint);
            Response getPet =given().spec(specForRequestCTJson)
                    .when().get(format("%s%s%s", URL, endpoint, petID));
            getPet.then().log().all().statusCode(statusCode)
                    .body(matchesJsonSchemaInClasspath(jsonSchema))
                    .body("id", equalTo(petID));
            deletePetById(petID, endpoint);
        }
        else if (statusCode.equals(404)) {
            Response getPet =given().spec(specForRequestCTJson)
                    .when().get(format("%s%s%s", URL, endpoint, petID));
            getPet.then().log().all().statusCode(statusCode)
                    .body(matchesJsonSchemaInClasspath(jsonSchema))
                    .body("message", equalTo("Pet not found"));
        }
        else {
            Response getPet =given().spec(specForRequestCTJson)
                    .when().get(format("%s%s", URL, endpoint));
            getPet.then().log().all().statusCode(statusCode).contentType(ContentType.XML);
        }
    }
    private void deletePetById(Integer petID, String endpoint) {
        given().when().delete(format("%s%s%s", URL, endpoint, petID));
    }
    private void createPet(String endpoint) {
        given().spec(specForRequestCTJson).body(PetStorePetValidBody)
                .when().post(format("%s%s", URL, endpoint));
    }
}
