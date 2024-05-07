package rest;

import api.AuthenticationController;
import dto.RegistrationBodyDto;
import org.testng.annotations.Test;

public class LoginTestsRest extends AuthenticationController {

    @Test

    private void loginPositiveTest() {
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(EMAIL)
                .password(PASSWORD)
                .build();
        String token = tokenResponseRegLog(registrationBodyDto, LOGIN_URL);
        System.out.println(token);
    }
}
