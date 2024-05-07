package rest;

import api.AuthenticationController;
import api.BaseApi;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class RegistrationTestsRest extends AuthenticationController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest() {
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .firstName("Fedor")
                .lastName("Ivanov")
                .username("fed" + new Random().nextInt(1000) + "@gmail.com")
                .password("Yjhf3546hs#")
                .build();

        Assert.assertEquals(statusCodeResponseRegistrationLogin(registrationBodyDto, REGISTRATION_URL), 200);
    }

    @Test
    public void registrationNegativeTest_wrongEmail() {
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .firstName("Fedor")
                .lastName("Ivanov")
                .username("fed" + new Random().nextInt(1000) + "gmailcom")
                .password("Yjhf3546hs#")
                .build();

        ErrorMessageDtoString errorMessage = bodyNegativeResponseRegLog(registrationBodyDto, REGISTRATION_URL);
        softAssert.assertEquals(errorMessage.getStatus(), 400);
        softAssert.assertEquals(errorMessage.getError(), "Bad Request");
        softAssert.assertTrue(errorMessage.getMessage().toString().contains("must be a well-formed email address"));
        softAssert.assertAll();

    }

}
