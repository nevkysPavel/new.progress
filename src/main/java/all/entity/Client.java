package all.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
@FieldDefaults(level = PRIVATE)
@SelectBeforeUpdate
public class Client {


    @Id
    @GenericGenerator(name = "id", strategy = "increment")
    @Column(name = "id")
    int id;

    @Column(name = "first_name")
    @NotNull

    String firstName;

    @Column(name = "last_name")
    @NotNull
    String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull
    Sex sex;//enum

    @Column(name = "years")
    @NotNull
    @Min(1900)

    int years;

    @Column(name = "height")
    @NotNull
    @Size(min = 40,max = 280)
    int height;


    @Column(name = "weight")
    @NotNull
    @Size(min = 30,max = 300)
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

