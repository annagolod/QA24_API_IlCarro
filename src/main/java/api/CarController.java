package api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import dto.*;
import org.testng.annotations.BeforeSuite;

import static com.jayway.restassured.RestAssured.given;

public class CarController implements BaseApi {

    public String token = "";
    RequestSpecification requestSpecification;

    @BeforeSuite
    public void getTokenCarController() {
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(EMAIL)
                .password(PASSWORD)
                .build();
        token = given()
                .body(registrationBodyDto)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + LOGIN_URL)
                .thenReturn()
                .getBody()
                .as(TokenDto.class)
                .getAccessToken();
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", token)
                .build();
    }

    private Response addNewCarResponse(CarDto car, String token) {
        return given()
                .body(car)
                .spec(requestSpecification)
                //.contentType(ContentType.JSON)
                //.header("Authorization", token)
                .when()
                .post(BASE_URL + ADDNEWCAR_URL)
                .thenReturn();
    }

    public int statusCodeAddNewCarResponse(CarDto car, String token) {
        return addNewCarResponse(car, token).getStatusCode();
    }

    public ErrorMessageDtoString bodyNegativeAddNewCarResponse(CarDto car, String token) {
        return addNewCarResponse(car, token).getBody().as(ErrorMessageDtoString.class);
    }

    private Response getAllUserCarResponse() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(BASE_URL + GET_ALL_USER_CARS_URL)
                .thenReturn();
    }

    public int statusCodeGetAllUserCarResponse() {
        return getAllUserCarResponse().getStatusCode();
    }

    public CarsDto bodyGetAllUserCarResponse(){
        return getAllUserCarResponse().getBody().as(CarsDto.class);
    }


}
