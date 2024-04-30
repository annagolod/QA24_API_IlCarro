package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class ErrorMessageDtoString {
    private String timestamp; //    string($date-time)
    private int status; //  integer($int32)
    private String error; //    string
    private Object message; //  string
    private String path; // string
}
