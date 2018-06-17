package all.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


// Сложно ли задать реистрацию и делать вход по лоину и паролю ?
//Идет заполнение данных и дальше перебрасывает на страницу с заполнением еда и активность.
//@Data
@Entity
@Table(name = "clients")
//@FieldDefaults(level = PRIVATE)
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

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

