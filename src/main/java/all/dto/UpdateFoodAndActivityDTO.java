package all.dto;

import all.entity.FoodAndActivity;


public class UpdateFoodAndActivityDTO {
    private int clientId;
    FoodAndActivity foodAndActivity;

    public UpdateFoodAndActivityDTO() {
    }

    public int getClientId() {
        return this.clientId;
    }

    public FoodAndActivity getFoodAndActivity() {
        return this.foodAndActivity;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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
        final Object this$foodAndActivity = this.getFoodAndActivity();
        final Object other$foodAndActivity = other.getFoodAndActivity();
        if (this$foodAndActivity == null ? other$foodAndActivity != null : !this$foodAndActivity.equals(other$foodAndActivity))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getClientId();
        final Object $foodAndActivity = this.getFoodAndActivity();
        result = result * PRIME + ($foodAndActivity == null ? 43 : $foodAndActivity.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof UpdateFoodAndActivityDTO;
    }

    public String toString() {
        return "UpdateFoodAndActivityDTO(clientId=" + this.getClientId() + ", foodAndActivity=" + this.getFoodAndActivity() + ")";
    }
}
