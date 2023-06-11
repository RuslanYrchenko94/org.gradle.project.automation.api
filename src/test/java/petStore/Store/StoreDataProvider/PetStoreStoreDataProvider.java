package petStore.Store.StoreDataProvider;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import static config.enums.PetStoreEndpoint.*;
import static globalConstants.Constants.*;

public class PetStoreStoreDataProvider {

    static Stream<Arguments> getPetStoreStoreInventory() {
        return Stream.of(
                // valid case
                Arguments.of(STORE_INVENTORY.getEndpoint(), CODE_OK, "json.schema.PetStore/receiveResponseGetStoreInventory.json")
        );
    }
    static Stream<Arguments> postPetStoreOrder() {
        return Stream.of(
                Arguments.of(STORE_ORDER.getEndpoint(), CODE_OK, "json.schema.PetStore/receiveResponsePostOrder.json", PetStoreStoreOrderValidBody),
                Arguments.of(STORE_ORDER.getEndpoint(), 400, "json.schema.PetStore/receiveResponsePostWithInvalidBody.json", PetStoreStoreOrderWithInvalidBody)
                );
    }
    static Stream<Arguments> getPetStoreOrderById() {
        return Stream.of(
                // valid case
                Arguments.of(STORE_ORDER.getEndpoint(), CODE_OK, orderID, "json.schema.PetStore/receiveResponsePostOrder.json"),
                // no record found
                Arguments.of(STORE_ORDER.getEndpoint(), 404, orderID, "json.schema.PetStore/receiveResponseGetNotFound.json"),
                //invalid param
                Arguments.of(STORE_ORDER.getEndpoint(), 405, orderID, "json.schema/")
        );
    }
    static Stream<Arguments> deletePetStoreOrder() {
        return Stream.of(
                // valid case
                Arguments.of(STORE_ORDER.getEndpoint(), CODE_OK, orderID, "json.schema.PetStore/receiveResponseDelete.json"),
                // no record found
                Arguments.of(STORE_ORDER.getEndpoint(), 404, orderID, "json.schema.PetStore/receiveResponseDelete.json"),
                //invalid param
                Arguments.of(STORE_ORDER.getEndpoint(), 405, orderID, "json.schema/")
        );
    }
}
