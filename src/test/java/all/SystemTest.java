package all;


import all.configuration.JavaConfiguration;
import all.entity.Client;
import all.entity.Sex;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.MysqldConfig;
import lombok.experimental.FieldDefaults;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.get;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_6_23;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {JavaConfiguration.class})
@FieldDefaults(level = PRIVATE)
public class SystemTest {
    static EmbeddedMysql mysqld;
    @Autowired
    private WebApplicationContext context;

    @BeforeClass
    public static void start()  {
        MysqldConfig config = aMysqldConfig(v5_6_23)
                .withCharset(UTF8)
                .withPort(3306)
                .withUser("pavel", "31228900")
                .build();


        mysqld = anEmbeddedMysql(config)
                .addSchema("process", ScriptResolver.classPathScript("createTableClientsTest.sql"))
                .start();

    }


    @AfterClass
    public static void stop() {
        mysqld.stop();
    }

    @Before
    public void setUp() {
        RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void ClientControllerRestTest() {
        //Save clients
        Client Den = new Client("Den", "Denis", Sex.MAN, 1990, 170, 70);
        given().contentType(ContentType.JSON).body(Den)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("years", equalTo(1990))
                .extract().as(Client.class);

        Client Borov = new Client("Borov", "Boris", Sex.MAN, 1991, 175, 80);
        given().contentType(ContentType.JSON).body(Borov)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("lastName", equalTo("Boris"))
                .extract().as(Client.class);

        Client Lucy = new Client("Lucy", "Lui", Sex.WOMAN, 1969, 170, 50);
        given().contentType(ContentType.JSON).body(Lucy)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("firstName", equalTo("Lucy"))
                .body("height", equalTo(170))
                .extract().as(Client.class);

        Client Maggy = new Client("Maggy", "Mag", Sex.WOMAN, 1975, 165, 55);
        given().contentType(ContentType.JSON).body(Maggy)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("firstName", equalTo("Maggy"))
                .body("lastName", equalTo("Mag"))
                .extract().as(Client.class);

        //Update client
//        Client newMaggy = new Client(4,"newMaggy", "newMag", Sex.MAN, 1976, 166, 56);
//        given().contentType(ContentType.JSON).body(Maggy).body(newMaggy)
//                .put("/food/api/clients/put/client")
//                .then()
//                .log().body();

        //Get client
        get("/food/api/clients/get/client/3")
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstName", equalTo("Lucy"))
                .body("lastName", equalTo("Lui"))
                .body("sex", equalTo("WOMAN"))
                .body("years", equalTo(1969))
                .log().body()
                .extract().as(Client.class);


        //Get listClients
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/food/api/clients/get/clients")
                .then()
                .statusCode(302)
                .log().all();


        //Delete client
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/food/api/clients/delete/client/3")
                .then()
                .statusCode(204);
    }
}
