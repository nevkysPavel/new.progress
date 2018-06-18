package all.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import static lombok.AccessLevel.PRIVATE;


// Сложно ли задать реистрацию и делать вход по лоину и паролю ?
//Идет заполнение данных и дальше перебрасывает на страницу с заполнением еда и активность.
@Data
@Entity
@AllArgsConstructor
@Table(name = "clients")
@FieldDefaults(level = PRIVATE)
public class Client {

    //Была проблема с генерацией id (не проходил saveClient) - изменил, работает
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "id",strategy = "increment")
    @Column(name = "id")
    int id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;
    // Будет выводиться сообщение введите male/female. Дальше независимо от регистра испол. в вычислениях.
    // Испол. регулярные выражения для проверки.
    @Enumerated(EnumType.STRING)
    Sex sex;//enum
    //Это временно, задать другим форматом и оптимально вычислять возраст, согласно введенной дате рождения
    //Стоит ли делать проверки на адекватность введенных данных ?
    // Каклй тип данных лучше использовать, если потом буду проводить расчеты.
    @Column(name ="years")
    int years;

    @Column(name ="height")
    int height;

    @Column(name ="weight")
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

