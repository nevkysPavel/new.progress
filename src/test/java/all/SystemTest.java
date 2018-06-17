package all;


import all.entity.Client;
import all.entity.Sex;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.ws.Response;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class SystemTest {

    @BeforeClass
    public static void setup() {

        RestAssured.port = Integer.valueOf(9999);

//        String basePath = System.getProperty("server.base");
//        if(basePath==null){
//            basePath = "/rest-garage-sample/";
//        }
//        RestAssured.basePath = basePath;

        String baseHost = "http://127.0.0.1";

        RestAssured.baseURI = baseHost;





    }

    @Test
    public void systemTestGetClient() {
        String string = get("/food/api/clients/get/client/3")
                .then().extract().body().asString();
        System.out.println(string);
//Given an app
        //Then add a user
        //Get user
        //Delete
        //Assure that it has been deleted


    }

    //Поменял автогенерацию id и пошло
    @Test
    public void systemTestSaveClient() {
        Client client = new Client("Nevkys", "Pavel", Sex.MAN, 1995, 175, 70);
        given().contentType(ContentType.JSON).body(client).when().post("/food/api/clients/post/client")
                .then().statusCode(201).and().assertThat().body("years",equalTo(1995));


    }

    @Test
    public void systemTestDeleteClient() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 12)
                .when()
                .delete("/food/api/clients/delete/client/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void systemTestGetListClients(){
//        List<Client> all = get("/food/api/clients/get/clients").then().assertThat().body()

    }


}
