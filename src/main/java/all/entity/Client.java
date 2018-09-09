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
    private Sex sex;//enum
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
//
//    public Client(int client_id, String firstName, String lastName, Sex sex, int years, int height, int weight, List<FoodAndActivity> foodAndActivities) {
//        this.client_id = client_id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.sex = sex;
//        this.years = years;
//        this.height = height;
//        this.weight = weight;
//        this.foodAndActivities = foodAndActivities;
//    }

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
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$sex = this.getSex();
        final Object other$sex = other.getSex();
        if (this$sex == null ? other$sex != null : !this$sex.equals(other$sex)) return false;
        if (this.getYears() != other.getYears()) return false;
        if (this.getHeight() != other.getHeight()) return false;
        if (this.getWeight() != other.getWeight()) return false;
        final Object this$foodAndActivities = this.getFoodAndActivities();
        final Object other$foodAndActivities = other.getFoodAndActivities();
        return this$foodAndActivities == null ? other$foodAndActivities == null : this$foodAndActivities.equals(other$foodAndActivities);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getClient_id();
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $sex = this.getSex();
        result = result * PRIME + ($sex == null ? 43 : $sex.hashCode());
        result = result * PRIME + this.getYears();
        result = result * PRIME + this.getHeight();
        result = result * PRIME + this.getWeight();
        final Object $foodAndActivities = this.getFoodAndActivities();
        result = result * PRIME + ($foodAndActivities == null ? 43 : $foodAndActivities.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
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
