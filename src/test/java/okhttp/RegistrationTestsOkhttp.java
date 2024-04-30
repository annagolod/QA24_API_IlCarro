package okhttp;

import api.BaseApi;
import com.google.gson.Gson;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Random;

public class RegistrationTestsOkhttp implements BaseApi {
    OkHttpClient okHttpClient = new OkHttpClient();
    Gson gson = new Gson();
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000) + 1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i + "bill@gmail.com")
                .password("Svndgs15371$")
                .firstName("Bill")
                .lastName("Bernaw")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(registrationBodyDto), JSON);
        Request request = new Request.Builder().url(BASE_URL + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();

        Response response;

        try {
            response = okHttpClient.newCall(request).execute();
            System.out.println(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(response.isSuccessful());
    }


    @Test
    public void registrationNegativeTest_emailWO_At() {
        int i = new Random().nextInt(1000) + 1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i + "billgmail.com")
                .password("Svndgs15371$")
                .firstName("Bill")
                .lastName("Bernaw")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(registrationBodyDto), JSON);
        Request request = new Request.Builder().url(BASE_URL + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();

        Response response;
        String responseJson;

        try {
            response = okHttpClient.newCall(request).execute();
            System.out.println(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            responseJson = response.body().string();
            System.out.println(responseJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ErrorMessageDtoString errorMessageDtoString = gson.fromJson(responseJson, ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getStatus(), 400);
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().contains("must be a well-formed email address"), "must be a well-formed email address");
        softAssert.assertEquals(errorMessageDtoString.getError(), "Bad Request");
        Assert.assertFalse(response.isSuccessful());
        softAssert.assertAll();
    }

}
