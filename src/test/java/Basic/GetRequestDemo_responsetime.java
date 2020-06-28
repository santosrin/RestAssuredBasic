package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class GetRequestDemo_responsetime {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI ="https://maps.googleapis.com";
        RestAssured.basePath="/maps/api";
    }

    @Test
    public void statusCodeVerification() {
//        Response response = given().
//                param("origins","Seattle").
//                param("units","imperial").
//                param("destinations","San+Francisco").
//                param("key","AIzaSyAlOSAUGOBn7RA7E4y-YkK55xUCWiPsfGc").
//        when().
//                get("/distancematrix/json").
//        then().
//                statusCode(200).extract().response();
//        System.out.println(response.getBody().asString());

        //long responsTime =
//        given().
//                param("origins","Washington,DC").
//                param("units","imperial").
//                param("destinations","New+York+City,NY").
//                param("key","AIzaSyAlOSAUGOBn7RA7E4y-YkK55xUCWiPsfGc").
//        when().
//               get("/distancematrix/json").timeIn(TimeUnit.SECONDS);//time();
        //System.out.println("Response time is " + responsTime);
                given().
                        param("origins","Washington,DC").
                        param("units","imperial").
                        param("destinations","New+York+City,NY").
                        param("key","AIzaSyAlOSAUGOBn7RA7E4y-YkK55xUCWiPsfGc").
                        when().
                        get("/distancematrix/json").then().
                        time(lessThan(1L),TimeUnit.MILLISECONDS).

                statusCode(200).
                and().rootPath("rows[0].elements[0].distance").
                body("text",equalTo("228 mi")).
                body("value",equalTo(367436)).
                contentType(ContentType.JSON);
    }
}
