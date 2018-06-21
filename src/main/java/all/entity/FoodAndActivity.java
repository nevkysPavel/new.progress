package all.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

/* Будет идти заполнение за день (дальше можно сделать с добавление)
/  Будет создана отдельная таблица и связана с конкретным пользователем

*/

@Data
@AllArgsConstructor
@Entity
@Table(name = "FOOD_AND_ACTIVITY")
@FieldDefaults(level = PRIVATE)
public class FoodAndActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    int id;
    @Column(name = "PROTEIN")
    short protein;
    @Column(name = "CARBOHYDRATE")
    short carbohydrate;
    @Column(name = "FAT")
    short fat;

}
