package petStore.User.UserDataProvider;



import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static config.enums.PetStoreEndpoint.USER;
import static globalConstants.Constants.*;

public class PetStoreUserDataProvider {
    static Stream<Arguments> postPetStoreUser() {
        return Stream.of(
                // valid case
                Arguments.of(USER.getEndpoint(), CODE_OK, "json.schema.PetStore/receiveResponsePostUser.json", PetStoreUserValidBody),
                // case with invalid body
                Arguments.of(USER.getEndpoint(), 400, "json.schema.PetStore/receiveResponsePostUser.json", PetStoreUserWithInvalidBody)
        );
    }
    static Stream<Arguments> getPetStoreUserByUserName() {
        return Stream.of(
                // valid case
                Arguments.of(USER.getEndpoint(), CODE_OK, Username, "json.schema.PetStore/receiveResponseGetUser.json"),
                // no record found
                Arguments.of(USER.getEndpoint(), 404, Username, "json.schema.PetStore/receiveResponseGetNotFound.json"),
                //invalid param
                Arguments.of(USER.getEndpoint(), 405, Username, "json.schema/")
        );
    }
    static Stream<Arguments> deletePetStoreUserByUserName() {
        return Stream.of(
                // valid case
                Arguments.of(USER.getEndpoint(), CODE_OK, Username, "json.schema.PetStore/receiveResponseDelete.json"),
                // no record found
                Arguments.of(USER.getEndpoint(), 404, Username, "json.schema/"),
                //invalid param
                Arguments.of(USER.getEndpoint(), 405, Username, "json.schema/")
        );
    }
}
