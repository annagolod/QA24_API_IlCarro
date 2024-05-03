package okhttp;

import api.BaseApi;
import com.google.gson.Gson;
import dto.CarDto;
import dto.RegistrationBodyDto;
import dto.ResponseMessageDto;
import dto.TokenDto;
import dto.enumclass.Fuel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Random;

public class DeleteCarBySerialNumberOkhttp implements BaseApi {

    OkHttpClient okHttpClient = new OkHttpClient();
    Gson gson = new Gson();
    SoftAssert softAssert = new SoftAssert();
    TokenDto token;
    String serialNumber;

    @BeforeClass
    public void loginPreCondition() throws IOException {

        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(EMAIL)
                .password(PASSWORD)
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(registrationBodyDto), JSON);
        Request request = new Request.Builder().url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = okHttpClient.newCall(request).execute();
            System.out.println(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        token = gson.fromJson(response.body().string(), TokenDto.class);
        System.out.println(token.getAccessToken());
    }

    @BeforeMethod
    private void AddNewCar() throws IOException {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("555-" + i)
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.DIESEL.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        serialNumber = car.getSerialNumber();
        System.out.println(serialNumber);
        RequestBody requestBody = RequestBody.create(gson.toJson(car), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADDNEWCAR_URL)
                .addHeader("Authorization", token.getAccessToken())
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
    }

    @Test
    public void DeleteCarBySerialNumberPositiveTest(){
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_CAR_URL+serialNumber)
                .addHeader("Authorization", token.getAccessToken())
                .delete()
                .build();

                Response response;
                String responseJson;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response == null)
            Assert.fail("response is null");
        else if (response.code() == 200) {
            try {
                responseJson = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ResponseMessageDto responseMessageDto = gson.fromJson(responseJson, ResponseMessageDto.class);
            Assert.assertEquals(responseMessageDto.getMessage(), "Car deleted successfully");
        } else
            Assert.fail("Error, response code--> " + response.code());
        Assert.assertTrue(response.isSuccessful());


    }

}
