package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.CarDto;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import dto.TokenDto;
import org.testng.annotations.BeforeSuite;

import static com.jayway.restassured.RestAssured.given;

public class CarController implements BaseApi {

    public String token = "";

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
    }

    private Response addNewCarResponse(CarDto car, String token) {
        return given()
                .body(car)
                .contentType(ContentType.JSON)
                .when()
                .header("Authorization", token)
                .post(BASE_URL + ADDNEWCAR_URL)
                .thenReturn();
    }

    public int statusCodeAddNewCarResponse(CarDto car, String token) {
        return addNewCarResponse(car, token).getStatusCode();
    }

    public ErrorMessageDtoString bodyNegativeAddNewCarResponse(CarDto car, String token) {
        return addNewCarResponse(car, token).getBody().as(ErrorMessageDtoString.class);
    }


}
