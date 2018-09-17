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
public class SystemTestMustache {
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
    public void TestControllerMustache() {
        //Save Clients
        Client Den = new Client("Den", "Denis", Sex.MAN, 1990, 170, 70);
        String clientDen = given().header("Content-Type", "application/x-www-form-urlencoded")
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
        Assert.assertTrue(clientDen.contains("Den"));
        Assert.assertTrue(clientDen.contains("Denis"));

        Client Maggy = new Client("Maggy", "Mag", Sex.WOMAN, 1975, 165, 55);
        String clientMaggy = given().header("Content-Type", "application/x-www-form-urlencoded")
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
        Assert.assertTrue(clientMaggy.contains("Maggy"));
        Assert.assertTrue(clientMaggy.contains("Mag"));

        Client Borov = new Client("Borov", "Boris", Sex.MAN, 1991, 175, 80);
        String clientBorov = given().header("Content-Type", "application/x-www-form-urlencoded")
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
        Assert.assertTrue(clientBorov.contains("Borov"));
        Assert.assertTrue(clientBorov.contains("Boris"));
        //Get Client By Id
        String getClientById = given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("getClientById?id=1")
                .then()
                .statusCode(200)
                .extract().asString();
        Assert.assertTrue(getClientById.contains("Den"));
        Assert.assertTrue(getClientById.contains("Denis"));
        //Get List of Clients
        String getListOfClients = given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("/getListOfClients")
                .then()
                .statusCode(200)
                .extract().asString();
        Assert.assertTrue(getListOfClients.contains("Den"));
        Assert.assertTrue(getListOfClients.contains("Boris"));
        Assert.assertTrue(getListOfClients.contains("Maggy"));
        //Update Client
        Client UpdateDen = new Client("NewDen", "NewDenis", Sex.WOMAN, 19900, 1700, 700);
        String updatedClient = given().header("Content-Type", "application/x-www-form-urlencoded")
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
        Assert.assertTrue(updatedClient.contains("NewDen"));
        Assert.assertTrue(updatedClient.contains("NewDenis"));
        //Delete Client
        given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("/deleteClientById?id=1")
                .then()
                .statusCode(200);
        //FixMe
        //Save FoodAndActivity
        FoodAndActivity foodAndActivity = new FoodAndActivity(5, 5, 5, KindOfSport.RUN, 10, Maggy);
        UpdateFoodAndActivityDTO uFaADTO = new UpdateFoodAndActivityDTO(foodAndActivity.getClient().getClient_id(), foodAndActivity);
        String saveFoodAndActivityResponse = given().header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("clientId", uFaADTO.getClientId())
                .formParam("protein", uFaADTO.getFoodAndActivity().getProtein())
                .formParam("carbohydrate", uFaADTO.getFoodAndActivity().getCarbohydrate())
                .formParam("fat", uFaADTO.getFoodAndActivity().getFat())
                .formParam("kindOfSport", uFaADTO.getFoodAndActivity().getKindOfSport())
                .formParam("durationOfTraining", uFaADTO.getFoodAndActivity().getDurationOfTraining())
                .when()
                .post("/saveFoodAndActivity")
                .then()
                .statusCode(200)
                .assertThat()
                .extract().asString();
        System.out.println(saveFoodAndActivityResponse);

        //Get Calories
        String responseCalories = given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("calories?id=2")
                .then()
                .statusCode(200)
                .extract().asString();
        Integer receive =Integer.parseInt(responseCalories.replaceAll("[^0-6]","").trim());
        Integer value = 1205;
        Assert.assertEquals(receive,value);

        //Get List of FoodAndActivity
        String getListOfFoodAndActivity = given().header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .get("getFoodAndActivityByIdClient?id=2")
                .then()
                .statusCode(200)
                .extract().asString();
       Assert.assertTrue(getListOfFoodAndActivity.contains("RUN"));
       //FixMe
       //Get FoodAndActivity By Date And Client Id
//        String i = given().header("Content-Type", "application/x-www-form-urlencoded")
//                .when()
//                .get("getFoodAndActivityByDateAndClientId?id=2?year=2018?month=09?day=16")
//                .then()
//                .statusCode(200)
//                .extract().asString();
//        System.out.println(i);


    }

}
