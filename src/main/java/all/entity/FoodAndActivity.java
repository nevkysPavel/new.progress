package all.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;


@Entity
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
    @JsonBackReference
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

    public FoodAndActivity(int id, int protein, int carbohydrate, int fat, KindOfSport kindOfSport, int durationOfTraining, Client client, LocalDate localDate) {
        this.id = id;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.kindOfSport = kindOfSport;
        this.durationOfTraining = durationOfTraining;
        this.client = client;
        this.localDate = localDate;
    }

    public FoodAndActivity() {
    }

    @PrePersist
    protected void onCreate() {
        localDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        localDate = LocalDate.now();
    }

    public int getId() {
        return this.id;
    }

    public int getProtein() {
        return this.protein;
    }

    public int getCarbohydrate() {
        return this.carbohydrate;
    }

    public int getFat() {
        return this.fat;
    }

    public KindOfSport getKindOfSport() {
        return this.kindOfSport;
    }

    public int getDurationOfTraining() {
        return this.durationOfTraining;
    }

    public Client getClient() {
        return this.client;
    }

    public LocalDate getLocalDate() {
        return this.localDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setKindOfSport(KindOfSport kindOfSport) {
        this.kindOfSport = kindOfSport;
    }

    public void setDurationOfTraining(int durationOfTraining) {
        this.durationOfTraining = durationOfTraining;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FoodAndActivity)) return false;
        final FoodAndActivity other = (FoodAndActivity) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        if (this.getProtein() != other.getProtein()) return false;
        if (this.getCarbohydrate() != other.getCarbohydrate()) return false;
        if (this.getFat() != other.getFat()) return false;
        final Object this$kindOfSport = this.getKindOfSport();
        final Object other$kindOfSport = other.getKindOfSport();
        if (this$kindOfSport == null ? other$kindOfSport != null : !this$kindOfSport.equals(other$kindOfSport))
            return false;
        if (this.getDurationOfTraining() != other.getDurationOfTraining()) return false;
        final Object this$client = this.getClient();
        final Object other$client = other.getClient();
        if (this$client == null ? other$client != null : !this$client.equals(other$client)) return false;
        final Object this$localDate = this.getLocalDate();
        final Object other$localDate = other.getLocalDate();
        return this$localDate == null ? other$localDate == null : this$localDate.equals(other$localDate);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        result = result * PRIME + this.getProtein();
        result = result * PRIME + this.getCarbohydrate();
        result = result * PRIME + this.getFat();
        final Object $kindOfSport = this.getKindOfSport();
        result = result * PRIME + ($kindOfSport == null ? 43 : $kindOfSport.hashCode());
        result = result * PRIME + this.getDurationOfTraining();
        final Object $client = this.getClient();
        result = result * PRIME + ($client == null ? 43 : $client.hashCode());
        final Object $localDate = this.getLocalDate();
        result = result * PRIME + ($localDate == null ? 43 : $localDate.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof FoodAndActivity;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}