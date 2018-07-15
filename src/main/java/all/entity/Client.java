package all.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//FixME
// Лучше испол. SEQUENCE
@Table(name = "clients")
@FieldDefaults(level = PRIVATE)
@SelectBeforeUpdate
public class Client {

    @Id
    @GenericGenerator(name = "client_id", strategy = "increment")
    @Column(name = "client_id")
    int id;

    @Column(name = "first_name")
    @NotNull
//    @Size(min=3, max = 50)
            String firstName;

    @Column(name = "last_name")
    @NotNull
//    @Size(min=3, max=30)
            String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull
    Sex sex;//enum

    @Column(name = "years")
    @NotNull
    int years;

    @Column(name = "height")
    @NotNull
//    @Size(min = 40,max = 280)
            int height;


    @Column(name = "weight")
    @NotNull
//    @Size(min = 30,max = 300)
            int weight;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<FoodAndActivity> foodAndActivities = new ArrayList<>();

    public Client(String firstName, String lastName, Sex sex, int years, int height, int weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.years = years;
        this.height = height;
        this.weight = weight;
    }

    public Client(int id, @NotNull String firstName, @NotNull String lastName, @NotNull Sex sex, @NotNull  int years, @NotNull int height, @NotNull int weight) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.years = years;
        this.height = height;
        this.weight = weight;
    }
}
