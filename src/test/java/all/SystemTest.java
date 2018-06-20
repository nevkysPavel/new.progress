package all;


import all.configuration.JavaConfiguration;
import all.entity.Client;
import all.entity.Sex;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.MysqldConfig;
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
import static org.hamcrest.CoreMatchers.equalTo;

//classes = {JavaConfiguration.class, WebAppInitializer.class}
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {JavaConfiguration.class})
public class SystemTest {

 @BeforeClass
         public static void start() throws InterruptedException {
     MysqldConfig config = aMysqldConfig(v5_6_23)
             .withCharset(UTF8)
             .withPort(3306)
             .withUser("pavel", "31228900")
             .build();


     EmbeddedMysql mysqld = anEmbeddedMysql(config)
             .addSchema("newprogress", ScriptResolver.classPathScript("createTableClientsTest.sql"))
             .start();

 }
    @Autowired
    private WebApplicationContext context;

    @Test
    public void systemTestGetClient() {
        String string = get("/food/api/clients/get/client/3")
                .then().statusCode(200).extract().body().asString();
        System.out.println(string);
    }

    @Before
    public void setUp() {
        RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //Поменял автогенерацию id и пошло
    @Test
    public void systemTestSaveClient() {
        Client client = new Client("Nevkys", "Pavel", Sex.MAN, 2100, 100, 70);
        given().contentType(ContentType.JSON).body(client)
                .when()
                .post("/food/api/clients/post/client")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("years", equalTo(2100));


    }
}

//    @Ignore
//    @Test
//    public void systemTestDeleteClient() {
//        given()
//                .contentType(ContentType.JSON)
//                .pathParam("id", 19)
//                .when()
//                .delete("/food/api/clients/delete/client/{id}")
//                .then()
//                .statusCode(204);
//    }

//    @Test
//    public void systemTestGetListClients(){
//       List<Client> all = get("/food/api/clients/get/clients").then().assertThat().body()
//
//    }
//
//}
//
