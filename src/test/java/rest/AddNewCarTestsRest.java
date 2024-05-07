package rest;

import api.CarController;
import dto.CarDto;
import dto.ErrorMessageDtoString;
import dto.enumclass.Fuel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTestsRest extends CarController {

    @Test
    public void addNewCarPositiveTest() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("333-" + i)
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.DIESEL.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Assert.assertEquals(statusCodeAddNewCarResponse(car, token), 200);
    }
    @Test
    public void addNewCarNegativeTest_emptyFieldSerialNumber(){
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.DIESEL.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        ErrorMessageDtoString errorMessage = bodyNegativeAddNewCarResponse(car, token);
        Assert.assertEquals(errorMessage.getError(), "Bad Request");
    }
    @Test
    public void addNewCarNegativeTest_wrongToken(){
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("333-" + i)
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.DIESEL.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Assert.assertEquals(statusCodeAddNewCarResponse(car, ""), 403);
    }
}
