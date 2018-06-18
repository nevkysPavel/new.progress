//package all;
//
//
//import all.entity.Client;
//import all.entity.Sex;
//import com.wix.mysql.EmbeddedMysql;
//import com.wix.mysql.ScriptResolver;
//import com.wix.mysql.config.MysqldConfig;
//import io.restassured.http.ContentType;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.util.concurrent.TimeUnit;
//
//import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
//import static com.wix.mysql.config.Charset.UTF8;
//import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
//import static com.wix.mysql.distribution.Version.v5_6_23;
//import static io.restassured.RestAssured.get;
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.equalTo;
//
//public class SystemTest {
//
//    @BeforeClass
//    public static void setup() {
//        MysqldConfig config = aMysqldConfig(v5_6_23)
//                .withCharset(UTF8)
//                .withPort(2215)
//                .withUser("differentUser", "anotherPassword")
//                .withTimeZone("Europe/Vilnius")
//                .withTimeout(2, TimeUnit.MINUTES)
//                .withServerVariable("max_connect_errors", 666)
//                .build();
//
//        EmbeddedMysql mysqld = anEmbeddedMysql(config)
//                .addSchema("aschema", ScriptResolver.classPathScript("createTableClientsTest.sql"))
//                .start();
//
////        RestAssured.port = Integer.valueOf(9999);
//
////        String basePath = System.getProperty("server.base");
////        if(basePath==null){
////            basePath = "/rest-garage-sample/";
////        }
////        RestAssured.basePath = basePath;
//
////        String baseHost = "http://127.0.0.1";
////
////        RestAssured.baseURI = baseHost;
//
//    }
//
//    @Test
//    public void systemTestGetClient() {
//
//        String string = get("/food/api/clients/get/client/3")
//                .then().extract().body().asString();
//        System.out.println(string);
//
//        Client client = new Client("Nevkys", "Pavel", Sex.MAN, 2100, 100, 70);
//        given().contentType(ContentType.JSON).body(client).when().post("/food/api/clients/post/client")
//                .then().statusCode(201).and().assertThat().body("years",equalTo(2100));
//
//    }
////    @Test
////    public void systemTestDeleteClient() {
////        given()
////                .contentType(ContentType.JSON)
////                .pathParam("id", 18)
////                .when()
////                .delete("/food/api/clients/delete/client/{id}")
////                .then()
////                .statusCode(204);
////    }
//
////    @Test
////    public void systemTestGetListClients(){
////       List<Client> all = get("/food/api/clients/get/clients").then().assertThat().body()
////
////    }
////
////    @Test
////    public void systemTestUpdateClient() {
////            given()
////                    .contentType(ContentType.JSON)
////                    .pathParam("id",)
////                    .put("/food/api/clients/put/client")
////                    .then()
////                    .body("")
////    }
//
//}
//
