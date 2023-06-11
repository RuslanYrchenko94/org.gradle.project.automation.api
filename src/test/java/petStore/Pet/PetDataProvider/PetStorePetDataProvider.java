package petStore.Pet.PetDataProvider;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import static config.enums.PetStoreEndpoint.*;
import static globalConstants.Constants.*;

public class PetStorePetDataProvider{
     static Stream<Arguments> getPetStorePetsByStatus() {
        return Stream.of(
                // valid case Finds Pets by sold status
                Arguments.of(PET_FIND_BY_STATUS.getEndpoint(), CODE_OK, STATUS, "sold", "category"),
                // valid case Finds Pets by pending status
                Arguments.of(PET_FIND_BY_STATUS.getEndpoint(), CODE_OK, STATUS, "pending", "category"),
                // valid case Finds Pets by available status
                Arguments.of(PET_FIND_BY_STATUS.getEndpoint(), CODE_OK, STATUS, "available", "category"),
                // invalid case Finds Pets by available status with invalid endpoint
                Arguments.of("/pet/findByStats", 404, STATUS, "available", "type")
        );
    }


    static Stream<Arguments> postPetStorePet() {
        return Stream.of(
                // valid case
                Arguments.of(PET.getEndpoint(), CODE_OK, petID, "json.schema.PetStore/receiveResponsePostPet.json", PetStorePetValidBody),
                //create pet with invalid ID
                Arguments.of(PET.getEndpoint(), 400, 0, "json.schema.PetStore/receiveResponsePostWithInvalidBody.json", PetStorePetBodyWithInvalidID),
                //create pet with invalid body
                Arguments.of(PET.getEndpoint(), 500, 0, "json.schema.PetStore/receiveResponsePostWithInvalidBody.json", PetStorePetBodyWithInvalidBody)
        );
    }
    static Stream<Arguments>postPetStorePetById() {
        return Stream.of(
                // valid case
                Arguments.of(PET.getEndpoint(), CODE_OK, petID, "json.schema.PetStore/receiveResponsePostPetById.json", "name=Spike&status=sold"),
                // no record found
                Arguments.of(PET.getEndpoint(), 404, petID, "json.schema.PetStore/receiveResponseGetNotFound.json", "name=Spike&status=sold")
        );
    }
    static Stream<Arguments>deletePetStorePetById(){
        return Stream.of(
                // valid case
                Arguments.of(PET.getEndpoint(), CODE_OK, petID, "json.schema.PetStore/receiveResponseDelete.json"),
                // no record found
                Arguments.of(PET.getEndpoint(), 404, petID, "json.schema.PetStore/receiveResponseDelete.json")
        );
    }
    static Stream<Arguments>getPetStorePetById(){
        return Stream.of(
                // valid case
                Arguments.of(PET.getEndpoint(), CODE_OK, petID, "json.schema.PetStore/receiveResponseGetPet.json"),
                // no record found
                Arguments.of(PET.getEndpoint(), 404, petID, "json.schema.PetStore/receiveResponseGetNotFound.json"),
                //invalid param
                Arguments.of(PET.getEndpoint(), 405, petID, "json.schema.PetStore/receiveResponseGetNotFound.json")
        );
    }
}
