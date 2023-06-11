package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class PetStoreUserModel {

    public Integer getId() {
        return id;
    }

    public PetStoreUserModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PetStoreUserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PetStoreUserModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PetStoreUserModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PetStoreUserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public PetStoreUserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PetStoreUserModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public PetStoreUserModel setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
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
