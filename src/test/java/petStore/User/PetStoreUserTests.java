package petStore.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.BaseTest;
import java.sql.*;

import static globalConstants.Constants.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreUserTests extends BaseTest {

    @MethodSource("petStore.User.UserDataProvider.PetStoreUserDataProvider#postPetStoreUser")
    @ParameterizedTest(name = "Create user, status code {1}")
    public void postPetStoreUserTest(String endpoint, Integer statusCode, String jsonSchema, String body) {
        Response createUser = given().spec(specForRequestCTJson)
                .body(body)
                .when().post(format("%s%s", URL, endpoint));
        if (createUser.statusCode() == 200) {
            addUserToDatabase(userID, Username);

            createUser.then().spec(specForResponse).statusCode(CODE_OK)
                .body(matchesJsonSchemaInClasspath(jsonSchema))
                .body("message", equalTo(selectUserFromDatabaseById(Username)));

            deleteUserByUsername(Username, endpoint);
            deleteUserFromDatabase(userID);
        }
        else {
            createUser.then().log().all().statusCode(statusCode)
                    .body(matchesJsonSchemaInClasspath(jsonSchema))
                    .body("message", equalTo("bad input"));
        }
    }
    @MethodSource("petStore.User.UserDataProvider.PetStoreUserDataProvider#getPetStoreUserByUserName")
    @ParameterizedTest(name = "Get user by username, status code {1}")
    public void getPetStoreUserByUserName(String endpoint, Integer statusCode, String Username, String jsonSchema){

        if(statusCode.equals(CODE_OK)){
            createUser(endpoint);
            Response getUser =given().spec(specForRequestCTJson)
                    .when().get(format("%s%s%s", URL, endpoint, Username));
            getUser.then().spec(specForResponse).statusCode(statusCode)
                    .body(matchesJsonSchemaInClasspath(jsonSchema))
                    .body("id", equalTo(userID))
                    .body("username", equalTo(Username));
            deleteUserByUsername(Username, endpoint);
        }
        else if (statusCode.equals(404)) {
            Response getUser =given().spec(specForRequestCTJson)
                    .when().get(format("%s%s%s", URL, endpoint, Username));
            getUser.then().log().all().statusCode(statusCode)
                    .body(matchesJsonSchemaInClasspath(jsonSchema))
                    .body("message", equalTo("User not found"));
        }
        else {
            Response getUser =given().spec(specForRequestCTJson)
                    .when().get(format("%s%s", URL, endpoint));
            getUser.then().log().all().statusCode(statusCode).contentType(ContentType.XML);
        }
    }
    @MethodSource("petStore.User.UserDataProvider.PetStoreUserDataProvider#deletePetStoreUserByUserName")
    @ParameterizedTest(name = "Delete user by username, status code {1}")
    public void deletePetStoreUserByUserName(String endpoint, Integer statusCode, String Username, String jsonSchema){

        if(statusCode.equals(CODE_OK)){
            createUser(endpoint);
            Response deleteUser =given().spec(specForRequestCTJson)
                    .when().delete(format("%s%s%s", URL, endpoint, Username));
            deleteUser.then().spec(specForResponse).statusCode(statusCode)
                    .body(matchesJsonSchemaInClasspath(jsonSchema))
                    .body("message", equalTo(Username));
        }
        else if (statusCode.equals(404)) {
            Response deleteUser =given().spec(specForRequestCTJson)
                    .when().delete(format("%s%s%s", URL, endpoint, Username));
            deleteUser.then().log().all().statusCode(statusCode);
        }
        else {
            Response deleteUser =given().spec(specForRequestCTJson)
                    .when().delete(format("%s%s", URL, endpoint));
            deleteUser.then().log().all().statusCode(statusCode).contentType(ContentType.XML);
        }
    }
    private void deleteUserByUsername(String username, String endpoint) {
        given().when().delete(format("%s%s%s", URL, endpoint, username));
    }
    private void createUser(String endpoint) {
        given().spec(specForRequestCTJson).body(PetStoreUserValidBody)
                .when().post(format("%s%s", URL, endpoint));
    }
    private void addUserToDatabase(Integer Id, String username){

        try (Connection connection = DriverManager.getConnection(database_url, database_username, database_password)) {
            String sql = "INSERT INTO test.users (id, username) VALUES ("+Id+", '"+username+"');commit;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private String selectUserFromDatabaseById(String username){
        int id = 0;

        try (Connection connection = DriverManager.getConnection(database_url, database_username, database_password)) {
            String sql = "select id from test.users where username = '"+username+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                // Отримайте дані з результату запиту
                id = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String strID = String.valueOf(id);
        return strID;
    }
    private void deleteUserFromDatabase(Integer id){

        try (Connection connection = DriverManager.getConnection(database_url, database_username, database_password)) {
            String sql = "delete from test.users where id = "+id+";commit;;commit;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
