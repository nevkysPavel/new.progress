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
import com.jayway.restassured.http.ContentType;
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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.get;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_6_23;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {JavaConfiguration.class})

public class SystemTestRest {
    private static EmbeddedMysql mysqld;
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
    public void TestRestCRUD() {
        //Save Clients
        Client Den = new Client("Den", "Denis", Sex.MAN, 1990, 170, 70);
        given().contentType(ContentType.JSON).body(Den)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("firstName", equalTo("Den"))
                .body("lastName", equalTo("Denis"))
                .body("sex", equalTo("MAN"))
                .body("years", equalTo(1990))
                .body("height", equalTo(170))
                .body("weight", equalTo(70));

        Client Borov = new Client("Borov", "Boris", Sex.MAN, 1991, 175, 80);
        given().contentType(ContentType.JSON).body(Borov)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("lastName", equalTo("Boris"));

        Client Lucy = new Client("Lucy", "Lui", Sex.WOMAN, 1969, 170, 50);
        given().contentType(ContentType.JSON).body(Lucy)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("firstName", equalTo("Lucy"))
                .body("height", equalTo(170));

        Client Maggy = new Client("Maggy", "Mag", Sex.WOMAN, 1975, 165, 55);
        given().contentType(ContentType.JSON).body(Maggy)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("firstName", equalTo("Maggy"))
                .body("lastName", equalTo("Mag"));

        //Get List
        given().contentType(ContentType.JSON)
                .when()
                .get("/food/api/clients/get/clients")
                .then()
                .statusCode(302);
    }

    @Test
    public void ClientControllerRestTestGetById() {
        Client Lucy = new Client("Mo", "My", Sex.WOMAN, 1969, 170, 50);
        given().contentType(ContentType.JSON).body(Lucy)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201);

        //Get client
        get("/food/api/clients/get/client/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstName", equalTo("Mo"))
                .body("lastName", equalTo("My"))
                .body("sex", equalTo("WOMAN"))
                .body("years", equalTo(1969))
                .body("height", equalTo(170))
                .body("weight", equalTo(50));
    }

    @Test
    public void ClientControllerRestTestPut() {
        Client Borov = new Client("Borov", "Boris", Sex.MAN, 1991, 175, 80);
        Borov = given().contentType(ContentType.JSON).body(Borov)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .extract().as(Client.class);
        Borov.setFirstName("UpdatedBorow");

        Client updatedBorov = given().contentType(ContentType.JSON).body(Borov)
                .when()
                .put("/food/api/clients/put/client")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().as(Client.class);
        Assert.assertEquals("UpdatedBorow", updatedBorov.getFirstName());
    }

    @Test
    public void ClientControllerRestTestDelete() {
        Client Den = new Client("Den", "Denis", Sex.MAN, 1990, 170, 70);
        given().contentType(ContentType.JSON).body(Den)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("years", equalTo(1990));

        given().contentType(ContentType.JSON)
                .when()
                .delete("/food/api/clients/delete/client/1")
                .then()
                .statusCode(204);
    }

    @Test
    public void ClientControllerRestTestGetCalorieCalculation() {
        Client Den = new Client("Den", "Denis", Sex.MAN, 1990, 170, 70);
        given().contentType(ContentType.JSON).body(Den)
                .when()
                .post("/food/api/clients/post/client");

        int amount = given().contentType(ContentType.JSON)
                .when()
                .get("food/api/clients/get/calorie/1")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract().as(Integer.class);
        Assert.assertEquals(1627, amount);
    }

    @Test
    public void ClientControllerRestTestSaveFoodAndActivity() {
        Client client = new Client("Den", "Denis", Sex.MAN, 1990, 170, 70);
        int clientId = clientService.saveClient(client);
        Client clientById = clientDao.getClientById(clientId);

        FoodAndActivity foodAndActivity = new FoodAndActivity(2, 2, 2, KindOfSport.GYM, 22, clientById);

        UpdateFoodAndActivityDTO updateFoodAndActivityDTO = new UpdateFoodAndActivityDTO();
        updateFoodAndActivityDTO.setClientId(clientId);
        updateFoodAndActivityDTO.setFoodAndActivity(foodAndActivity);
        given().contentType(ContentType.JSON).body(updateFoodAndActivityDTO)
                .when()
                .post("food/api/clients/post/foodAndActivity")
                .then()
                .statusCode(201);
    }

    @Test
    public void FoodAndActivityRestTestGetFoodAndActivityByIdClient() {
        //Given a Client
        Client client = new Client("Den", "Denis", Sex.MAN, 1990, 170, 70);
        int clientId = clientService.saveClient(client);
        Client clientById = clientDao.getClientById(clientId);
        //than save FoodAndActivity
        FoodAndActivity foodAndActivity1 = new FoodAndActivity(2, 2, 2, KindOfSport.GYM, 22, clientById);
        FoodAndActivity foodAndActivity2 = new FoodAndActivity(20, 50, 25, KindOfSport.RUN, 40, clientById);
        FoodAndActivity foodAndActivity3 = new FoodAndActivity(200, 200, 55, KindOfSport.SWIM, 33, clientById);

        foodAndActivityDao.saveFoodAndActivity(foodAndActivity1);
        foodAndActivityDao.saveFoodAndActivity(foodAndActivity2);
        foodAndActivityDao.saveFoodAndActivity(foodAndActivity3);

         given().contentType(ContentType.JSON)
                .when()
                .get("/food/getFoodAndActivityByIdClient/1")
                .then()
                .statusCode(302);
    }

    @Test
    public void FoodAndActivityRestTestGetFoodAndActivityByDateAndClientId() {
        Map<String, String> map = new HashMap<>();
        //Given a Client
        Client client = new Client("Den", "Denis", Sex.MAN, 1990, 170, 70);
        int clientId = clientService.saveClient(client);
        Client clientById = clientDao.getClientById(clientId);
        //than save FoodAndActivity
        FoodAndActivity foodAndActivity = new FoodAndActivity(2, 2, 2, KindOfSport.GYM, 22, clientById);
        int foodAndActivityId = foodAndActivityDao.saveFoodAndActivity(foodAndActivity);
        map.put("client_id", String.valueOf(clientId));
        map.put("data", LocalDate.now().toString());

        get("food/by_local_date/", map)
                .then();
    }
}