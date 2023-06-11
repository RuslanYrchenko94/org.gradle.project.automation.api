package dataModels;

import models.PetStoreOrderModel;
import models.pet.Category;
import models.pet.PetStorePetModel;
import models.PetStoreUserModel;
import models.pet.TagsItem;


import java.util.Collections;

import static globalConstants.Constants.datetime;


public class PetStoreData {
   public static PetStoreUserModel postCreateUser(int id, String userName) {

        return new PetStoreUserModel()
                .setId(id)
                .setUsername(userName)
                .setFirstName("Ruslan")
                .setLastName("Yurchenko")
                .setEmail("test@gmail.com")
                .setPassword("testuser")
                .setPhone("+380960913814")
                .setUserStatus(0);
   }

   public static PetStorePetModel postCreatePet(Integer petId) {
       return new PetStorePetModel()
               .setName("Piki")
               .setPhotoUrls("http://py.jpg")
               .setId(petId)
               .setCategory(new Category()
                       .setCategoryId(555)
                       .setCategoryName("pekines"))
               .setTags(Collections.singletonList(new TagsItem()
                       .setTagName("lovely dog")
                       .setTagId(1903)))
               .setStatus("available");}
    public static PetStoreOrderModel postCreateOrder(Integer orderID) {

        return new PetStoreOrderModel()
                .setId(orderID)
                .setPetId(777)
                .setQuantity(5)
                .setShipDate(datetime.toString())
                .setStatus("placed")
                .setComplete(true);

   }
}