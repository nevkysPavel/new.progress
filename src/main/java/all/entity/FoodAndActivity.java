package all.entity;

import javax.persistence.*;

/* Будет идти заполнение за день (дальше можно сделать с добавление)
/  Будет создана отдельная таблица и связана с конкретным пользователем
*/

//dsfdsf
@Entity
@Table(name = "FOOD_AND_ACTIVITY")
public class FoodAndActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    int id;
    @Column(name ="PROTEIN")
    short protein;
    @Column(name ="CARBOHYDRATE")
    short carbohydrate;
    @Column(name ="FAT")
    short fat;



}
