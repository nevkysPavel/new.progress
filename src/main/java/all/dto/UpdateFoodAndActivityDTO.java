package all.dto;

import all.entity.FoodAndActivity;

public class UpdateFoodAndActivityDTO {
    private int clientId;
    private FoodAndActivity foodAndActivity;

    public UpdateFoodAndActivityDTO() {
    }

    public UpdateFoodAndActivityDTO(int clientId, FoodAndActivity foodAndActivity) {
        this.clientId = clientId;
        this.foodAndActivity = foodAndActivity;
    }

    public int getClientId() {
        return this.clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public FoodAndActivity getFoodAndActivity() {
        return this.foodAndActivity;
    }

    public void setFoodAndActivity(FoodAndActivity foodAndActivity) {
        this.foodAndActivity = foodAndActivity;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateFoodAndActivityDTO)) return false;
        final UpdateFoodAndActivityDTO other = (UpdateFoodAndActivityDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getClientId() != other.getClientId()) return false;
        final Object thisFoodAndActivity1 = this.getFoodAndActivity();
        final Object other$foodAndActivity = other.getFoodAndActivity();
        return thisFoodAndActivity1 == null ? other$foodAndActivity == null : thisFoodAndActivity1.equals(other$foodAndActivity);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getClientId();
        final Object foodAndActivity1 = this.getFoodAndActivity();
        result = result * PRIME + (foodAndActivity1 == null ? 43 : foodAndActivity1.hashCode());
        return result;
    }

    private boolean canEqual(Object other) {
        return other instanceof UpdateFoodAndActivityDTO;
    }

    public String toString() {
        return "UpdateFoodAndActivityDTO(clientId=" + this.getClientId() + ", foodAndActivity=" + this.getFoodAndActivity() + ")";
    }
}
