package dto;

import api.BaseApi;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class RegistrationBodyDto {

    private String username; //*    string
    private String password; // string
    private String firstName;// string
    private String lastName; // string
}
