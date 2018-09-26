package all.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@SelectBeforeUpdate
public class Client {

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private
    List<FoodAndActivity> foodAndActivities = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int client_id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(name = "years")
    private int years;
    @Column(name = "height")
    private int height;
    @Column(name = "weight")
    private int weight;

    public Client(String firstName, String lastName, Sex sex, int years, int height, int weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.years = years;
        this.height = height;
        this.weight = weight;
    }

    public Client(int client_id, String firstName, String lastName, Sex sex, int years, int height, int weight) {
        this.client_id = client_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.years = years;
        this.height = height;
        this.weight = weight;
    }

    public Client(int client_id, String firstName, String lastName, Sex sex, int years, int height, int weight, List<FoodAndActivity> foodAndActivities) {
        this.client_id = client_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.years = years;
        this.height = height;
        this.weight = weight;
        this.foodAndActivities = foodAndActivities;
    }

    public Client() {
    }

    public int getClient_id() {
        return this.client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return this.sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getYears() {
        return this.years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<FoodAndActivity> getFoodAndActivities() {
        return this.foodAndActivities;
    }

    public void setFoodAndActivities(List<FoodAndActivity> foodAndActivities) {
        this.foodAndActivities = foodAndActivities;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Client)) return false;
        final Client other = (Client) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getClient_id() != other.getClient_id()) return false;
        final Object thisFirstName = this.getFirstName();
        final Object otherFirstName = other.getFirstName();
        if (thisFirstName == null ? otherFirstName != null : !thisFirstName.equals(otherFirstName)) return false;
        final Object thisLastName = this.getLastName();
        final Object otherLastName = other.getLastName();
        if (thisLastName == null ? otherLastName != null : !thisLastName.equals(otherLastName)) return false;
        final Object thisSex = this.getSex();
        final Object otherSex = other.getSex();
        if (thisSex == null ? otherSex != null : !thisSex.equals(otherSex)) return false;
        if (this.getYears() != other.getYears()) return false;
        if (this.getHeight() != other.getHeight()) return false;
        if (this.getWeight() != other.getWeight()) return false;
        final Object thisFoodAndActivities = this.getFoodAndActivities();
        final Object otherFoodAndActivities = other.getFoodAndActivities();
        return thisFoodAndActivities == null ? otherFoodAndActivities == null : thisFoodAndActivities.equals(otherFoodAndActivities);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getClient_id();
        final Object firstName1 = this.getFirstName();
        result = result * PRIME + (firstName1 == null ? 43 : firstName1.hashCode());
        final Object lastName1 = this.getLastName();
        result = result * PRIME + (lastName1 == null ? 43 : lastName1.hashCode());
        final Object sex1 = this.getSex();
        result = result * PRIME + (sex1 == null ? 43 : sex1.hashCode());
        result = result * PRIME + this.getYears();
        result = result * PRIME + this.getHeight();
        result = result * PRIME + this.getWeight();
        final Object foodAndActivities1 = this.getFoodAndActivities();
        result = result * PRIME + (foodAndActivities1 == null ? 43 : foodAndActivities1.hashCode());
        return result;
    }

    private boolean canEqual(Object other) {
        return other instanceof Client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", years=" + years +
                ", height=" + height +
                ", weight=" + weight +
                ", foodAndActivities=" + foodAndActivities +
                '}';
    }
}
