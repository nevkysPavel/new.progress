package all.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
@FieldDefaults(level = PRIVATE)
public class Client {


    @Id
    @GenericGenerator(name = "id", strategy = "increment")
    @Column(name = "id")
    int id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Enumerated(EnumType.STRING)
    Sex sex;//enum

    @Column(name = "years")
    int years;

    @Column(name = "height")
    int height;

    @Column(name = "weight")
    int weight;

    public Client(String firstName, String lastName, Sex sex, int years, int height, int weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.years = years;
        this.height = height;
        this.weight = weight;
    }
}

