package models;

public class PetStoreOrderModel{

    public int getPetId() {
        return petId;
    }

    public PetStoreOrderModel setPetId(int petId) {
        this.petId = petId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public PetStoreOrderModel setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getId() {
        return id;
    }

    public PetStoreOrderModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getShipDate() {
        return shipDate;
    }

    public PetStoreOrderModel setShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public boolean isComplete() {
        return complete;
    }

    public PetStoreOrderModel setComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PetStoreOrderModel setStatus(String status) {
        this.status = status;
        return this;
    }

    private int petId;
    private int quantity;
    private int id;
    private String shipDate;
    private boolean complete;
    private String status;

    @Override
    public String toString(){
        return
                "{\"id\":"+id+"," +
                        "\"petId\":"+petId+"," +
                        "\"quantity\":"+quantity+"," +
                        "\"shipDate\":\""+shipDate+"\"," +
                        "\"status\":\""+status+"\"," +
                        "\"complete\":"+complete+"}";
    }
}
