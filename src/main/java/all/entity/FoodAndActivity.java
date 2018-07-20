package all.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    Client client;
    @Column
    LocalDate localDate;

    public FoodAndActivity(int protein, int carbohydrate, int fat, KindOfSport kindOfSport, int durationOfTraining, Client client) {
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.kindOfSport = kindOfSport;
        this.durationOfTraining = durationOfTraining;
        this.client = client;
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