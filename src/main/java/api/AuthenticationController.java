package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import dto.TokenDto;

import static com.jayway.restassured.RestAssured.given;

public class AuthenticationController implements BaseApi {

    public Response registrationLogin(RegistrationBodyDto registrationBodyDto, String url) {
        return given()
                .body(registrationBodyDto)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + url)
                .thenReturn();

    }

    public int statusCodeResponseRegistrationLogin(RegistrationBodyDto registrationBodyDto, String url) {
        return registrationLogin(registrationBodyDto, url).getStatusCode();
    }

    public String tokenResponseRegLog(RegistrationBodyDto registrationBodyDto, String url) {
        return registrationLogin(registrationBodyDto, url).getBody().as(TokenDto.class).getAccessToken();
    }

    public ErrorMessageDtoString bodyNegativeResponseRegLog(RegistrationBodyDto registrationBodyDto, String url) {
        return registrationLogin(registrationBodyDto, url).getBody().as(ErrorMessageDtoString.class);
    }
}
