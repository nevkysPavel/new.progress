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

    LocalDate localDate;
    @Id
    @GenericGenerator(name = "id", strategy = "increment")
    @Column(name = "id")
    int id;
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    Client client;

    public FoodAndActivity(LocalDate localDate, int id, int protein, int carbohydrate, int fat, KindOfSport kindOfSport, int durationOfTraining) {
        this.localDate = localDate;
        this.id = id;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.kindOfSport = kindOfSport;
        this.durationOfTraining = durationOfTraining;
    }

    public FoodAndActivity(int id, int protein, int carbohydrate, int fat, KindOfSport kindOfSport, int durationOfTraining) {
        this.id = id;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.kindOfSport = kindOfSport;
        this.durationOfTraining = durationOfTraining;

    }

    @PrePersist
    protected void onCreate() {
        localDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        localDate = LocalDate.now();
    }
}