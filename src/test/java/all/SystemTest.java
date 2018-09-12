package all;


import all.configuration.JavaConfiguration;
import all.dao.ClientDao;
import all.dao.FoodAndActivityDao;
import all.entity.Client;
import all.entity.Sex;
import all.service.ClientService;
import all.service.FoodAndActivityService;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.MysqldConfig;
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

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_6_23;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {JavaConfiguration.class})
public class SystemTest {
    static EmbeddedMysql mysqld;
    @Autowired
    ClientDao clientDao;
    @Autowired
    ClientService clientService;
    @Autowired
    FoodAndActivityService foodAndActivityService;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FoodAndActivityDao foodAndActivityDao;

    @BeforeClass
    public static void start() {
        MysqldConfig config = aMysqldConfig(v5_6_23)
                .withCharset(UTF8)
                .withPort(3306)
                .withUser("pavel", "31228900")
                .build();

        mysqld = anEmbeddedMysql(config)
                .addSchema("process", ScriptResolver.classPathScript("createTablesForTests.sql"))
                .start();
    }

    @AfterClass
    public static void stop() {
        mysqld.stop();
    }

    @Before
    public void setUp() {
        RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mysqld.reloadSchema("process", ScriptResolver.classPathScript("createTablesForTests.sql"));
    }

    @Test
    public void ClientControllerTestSaveAndGetListAndDelete() {
        //Save Clients
        Client Den = new Client("Den", "Denis", Sex.MAN, 1990, 170, 70);
        String a = given().header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("firstName", Den.getFirstName())
                .formParam("lastName", Den.getLastName())
                .formParam("sex",Den.getSex())
                .formParam("years",Den.getYears())
                .formParam("height", Den.getHeight())
                .formParam("weight", Den.getWeight())
                .when()
                .post("/savedClient")
                .then()
                .statusCode(200)
                .assertThat()
                //.body("client_id", equalTo("1"))
                .extract().asString();
        System.out.println(a);

        Client Maggy = new Client("Maggy", "Mag", Sex.WOMAN, 1975, 165, 55);
        String b = given().header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("firstName", Maggy.getFirstName())
                .formParam("lastName", Maggy.getLastName())
                .formParam("sex",Maggy.getSex())
                .formParam("years",Maggy.getYears())
                .formParam("height", Maggy.getHeight())
                .formParam("weight", Maggy.getWeight())
                .when()
                .post("/savedClient")
                .then()
                .statusCode(200)
                .assertThat()
                //.body("client_id", equalTo("1"))
                .extract().asString();
        System.out.println(b);

        given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("getClientById?id=1")//How can I put here an ID ?
                .then()
                .statusCode(200)
                .log().all();


        //Get List of Clients
//        String c = given().header("Content-Type", "application/x-www-form-urlencoded")
//                .when()
//                .get("/getListOfClients")
//                .then()
//                .statusCode(200)
//                .log().all()
//                .extract().asString();

        //Get Client By Id






    }
}
