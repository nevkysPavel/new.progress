package all.dto;

import all.entity.FoodAndActivity;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class UpdateFoodAndActivityDTO {
    int clientId;
    FoodAndActivity foodAndActivity;
}
