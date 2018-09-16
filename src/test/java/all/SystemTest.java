package all;


import all.configuration.JavaConfiguration;
import all.dao.ClientDao;
import all.dao.FoodAndActivityDao;
import all.dto.UpdateFoodAndActivityDTO;
import all.entity.Client;
import all.entity.FoodAndActivity;
import all.entity.KindOfSport;
import all.entity.Sex;
import all.service.ClientService;
import all.service.FoodAndActivityService;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.MysqldConfig;
import org.junit.*;
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
                .formParam("sex", Den.getSex())
                .formParam("years", Den.getYears())
                .formParam("height", Den.getHeight())
                .formParam("weight", Den.getWeight())
                .when()
                .post("/savedClient")
                .then()
                .statusCode(200)
                .extract().asString();
        Assert.assertTrue(a.contains("Den"));
        Assert.assertTrue(a.contains("Denis"));
        Client Maggy = new Client("Maggy", "Mag", Sex.WOMAN, 1975, 165, 55);
        String b = given().header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("firstName", Maggy.getFirstName())
                .formParam("lastName", Maggy.getLastName())
                .formParam("sex", Maggy.getSex())
                .formParam("years", Maggy.getYears())
                .formParam("height", Maggy.getHeight())
                .formParam("weight", Maggy.getWeight())
                .when()
                .post("/savedClient")
                .then()
                .statusCode(200)
                .extract().asString();
        Maggy.setClient_id(2);
        Assert.assertTrue(b.contains("Maggy"));
        Assert.assertTrue(b.contains("Mag"));
        Client Borov = new Client("Borov", "Boris", Sex.MAN, 1991, 175, 80);
        String c = given().header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("firstName", Borov.getFirstName())
                .formParam("lastName", Borov.getLastName())
                .formParam("sex", Borov.getSex())
                .formParam("years", Borov.getYears())
                .formParam("height", Borov.getHeight())
                .formParam("weight", Borov.getWeight())
                .when()
                .post("/savedClient")
                .then()
                .statusCode(200)
                .extract().asString();
        Assert.assertTrue(c.contains("Borov"));
        Assert.assertTrue(c.contains("Boris"));
        //Get Client By Id
        String d = given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("getClientById?id=1")
                .then()
                .statusCode(200)
                .extract().asString();
        Assert.assertTrue(d.contains("Den"));
        Assert.assertTrue(d.contains("Denis"));
        //Get List of Clients
        String e = given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("/getListOfClients")
                .then()
                .statusCode(200)
                .extract().asString();
        Assert.assertTrue(e.contains("Den"));
        Assert.assertTrue(e.contains("Boris"));
        //Update Client
        Client UpdateDen = new Client("NewDen", "NewDenis", Sex.WOMAN, 19900, 1700, 700);
        String f = given().header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("client_id", 1)
                .formParam("firstName", UpdateDen.getFirstName())
                .formParam("lastName", UpdateDen.getLastName())
                .formParam("sex", UpdateDen.getSex())
                .formParam("years", UpdateDen.getYears())
                .formParam("height", UpdateDen.getHeight())
                .formParam("weight", UpdateDen.getWeight())
                .when()
                .post("/updatedClient")
                .then()
                .statusCode(200)
                .extract().asString();
        Assert.assertTrue(f.contains("NewDen"));
        Assert.assertTrue(f.contains("NewDenis"));
        //Delete Client
        given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("/deleteClientById?id=1")
                .then()
                .statusCode(200);
        //Save FoodAndActivity
        FoodAndActivity foodAndActivity = new FoodAndActivity(5,5,5, KindOfSport.RUN,10,Maggy);
        UpdateFoodAndActivityDTO uFaADTO = new UpdateFoodAndActivityDTO(foodAndActivity.getClient().getClient_id(),foodAndActivity);
        String saveFoodAndActivityResponse = given().header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("clientId", uFaADTO.getClientId())
                .formParam("protein",uFaADTO.getFoodAndActivity().getProtein())
                .formParam("carbohydrate",uFaADTO.getFoodAndActivity().getCarbohydrate())
                .formParam("fat",uFaADTO.getFoodAndActivity().getFat())
                .formParam("kindOfSport",uFaADTO.getFoodAndActivity().getKindOfSport())
                .formParam("durationOfTraining",uFaADTO.getFoodAndActivity().getDurationOfTraining())
                .when()
                .post("/saveFoodAndActicity")
                .then()
                .statusCode(200)
                .assertThat()
                .extract().asString();
        System.out.println(saveFoodAndActivityResponse);
        //Get Calories
        String g = given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("calories?id=2")
                .then()
                .statusCode(200)
                .extract().asString();
        System.out.println(g);
    }

}
