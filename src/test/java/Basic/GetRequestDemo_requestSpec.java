package Basic;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GetRequestDemo_requestSpec {

    RequestSpecBuilder requestSpecBuilder;
    static RequestSpecification requestSpecification;
    @BeforeClass
    public void setUp() {
//        RestAssured.baseURI ="https://maps.googleapis.com";
//        RestAssured.basePath="/maps/api";
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://maps.googleapis.com");
        requestSpecBuilder.setBasePath("/maps/api");
        requestSpecification = requestSpecBuilder.build();
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

        given().spec(requestSpecification).
                param("origins","Washington,DC").
                param("units","imperial").
                param("destinations","New+York+City,NY").
                param("key","AIzaSyAlOSAUGOBn7RA7E4y-YkK55xUCWiPsfGc").
        when().
               get("/distancematrix/json").
        then().
                statusCode(200).
                and().rootPath("rows[0].elements[0].distance").
                body("text",equalTo("228 mi")).
                body("value",equalTo(367436)).
                contentType(ContentType.JSON);
    }
}
