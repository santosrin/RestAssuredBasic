package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;


public class GetRequestDemo_Assert {

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

        given().
                param("origins","Washington,DC").
                param("units","imperial").
                param("destinations","New+York+City,NY").
                param("key","AIzaSyAlOSAUGOBn7RA7E4y-YkK55xUCWiPsfGc").
        when().
               get("/distancematrix/json").
        then().log().body().
                statusCode(200).
                and().
                body("rows[0].elements[0].distance.text",equalTo("228 mi")).
                body("destination_addresses[0]",equalTo("New York, NY, USA")).
                contentType(ContentType.JSON);
    }
}
