package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Setter
@Accessors(fluent = true)

public class PetStoreUserModel {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("userStatus")
    private Integer userStatus;
    @Override
    public String toString() {
        return
                "{\"id\":"+id+"," +
                        "\"username\":\""+username+"\"," +
                        "\"firstName\":\""+firstName+"\"," +
                        "\"lastName\":\""+lastName+"\"," +
                        "\"email\":\""+email+"\"," +
                        "\"password\":\""+password+"\"," +
                        "\"phone\":\""+phone+"\"," +
                        "\"userStatus\":"+userStatus+"}";



    }
}
