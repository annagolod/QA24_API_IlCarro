package rest;

import api.CarController;
import dto.CarDto;
import dto.CarsDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllUserCarsTestsRest extends CarController {

    @Test
    public void getAllUserCarPositiveTest(){
        Assert.assertEquals(statusCodeGetAllUserCarResponse(), 200);
    }

    @Test
    public void getAllUserCarPositiveTest_bodyResponse(){
        CarsDto carsDto = bodyGetAllUserCarResponse();
        for (CarDto car: carsDto.getCars()){
            System.out.println(car.getSerialNumber());
        }
    }
}
