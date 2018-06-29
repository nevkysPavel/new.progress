package all.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food_and_activity")
@FieldDefaults(level = PRIVATE)
public class FoodAndActivity {

    @Id
    @GenericGenerator(name = "id", strategy = "increment")
    @Column(name = "id")
    int id;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "protein")
    int protein;

    @Column(name = "carbohydrate")
    int carbohydrate;

    @Column(name = "fat")
    int fat;

    @Enumerated(EnumType.STRING)
    KindOfSport kindOfSport;

    @Column(name = "duration_of_training")
    int durationOfTraining;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    Client client;


    public FoodAndActivity( short protein, short carbohydrate, short fat, KindOfSport kindOfSport, int durationOfTraining) {
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.kindOfSport = kindOfSport;
        this.durationOfTraining = durationOfTraining;
    }
}