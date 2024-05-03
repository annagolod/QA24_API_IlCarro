package dto;

import dto.enumclass.Fuel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder

public class CarDto {
    private String serialNumber; //": "string",
    private String manufacture; //": "string",
    private String model; //": "string",
    private String year; //": "string",
    private String fuel; //": "string",
    //Fuel fuel;
    private int seats; //": 0,
    private String carClass; //": "string",
    private double pricePerDay;//": 0,
    private String about; //: "string",
    private String city; //": "string",
    private double lat; //": 0,
    private double lng; //": 0,
    private String image; //": "string",
    private String owner; //": "string",
    private List<BookedDto> bookedPeriods;

}
