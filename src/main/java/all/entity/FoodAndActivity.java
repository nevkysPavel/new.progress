package all.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "food_and_activity")
public class FoodAndActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "protein")
    private int protein;
    @Column(name = "carbohydrate")
    private int carbohydrate;
    @Column(name = "fat")
    private int fat;
    @Enumerated(EnumType.STRING)
    private KindOfSport kindOfSport;
    @Column(name = "duration_of_training")
    private int durationOfTraining;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "client_id")
    private Client client;
    @Column
    private LocalDate localDate;

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

    public void setId(int id) {
        this.id = id;
    }

    public int getProtein() {
        return this.protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbohydrate() {
        return this.carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public int getFat() {
        return this.fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public KindOfSport getKindOfSport() {
        return this.kindOfSport;
    }

    public void setKindOfSport(KindOfSport kindOfSport) {
        this.kindOfSport = kindOfSport;
    }

    public int getDurationOfTraining() {
        return this.durationOfTraining;
    }

    public void setDurationOfTraining(int durationOfTraining) {
        this.durationOfTraining = durationOfTraining;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getLocalDate() {
        return this.localDate;
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
        final Object thisKindOfSport = this.getKindOfSport();
        final Object otherKindOfSport = other.getKindOfSport();
        if (thisKindOfSport == null ? otherKindOfSport != null : !thisKindOfSport.equals(otherKindOfSport))
            return false;
        if (this.getDurationOfTraining() != other.getDurationOfTraining()) return false;
        final Object thisClient = this.getClient();
        final Object otherClient = other.getClient();
        if (thisClient == null ? otherClient != null : !thisClient.equals(otherClient)) return false;
        final Object thisLocalDate = this.getLocalDate();
        final Object otherLocalDate = other.getLocalDate();
        return thisLocalDate == null ? otherLocalDate == null : thisLocalDate.equals(otherLocalDate);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        result = result * PRIME + this.getProtein();
        result = result * PRIME + this.getCarbohydrate();
        result = result * PRIME + this.getFat();
        final Object kindOfSport = this.getKindOfSport();
        result = result * PRIME + (kindOfSport == null ? 43 : kindOfSport.hashCode());
        result = result * PRIME + this.getDurationOfTraining();
        final Object client = this.getClient();
        result = result * PRIME + (client == null ? 43 : client.hashCode());
        final Object localDate = this.getLocalDate();
        result = result * PRIME + (localDate == null ? 43 : localDate.hashCode());
        return result;
    }

    private boolean canEqual(Object other) {
        return other instanceof FoodAndActivity;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}