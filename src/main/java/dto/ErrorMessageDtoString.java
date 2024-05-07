package dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ErrorMessageDtoString {
    private String timestamp; //    string($date-time)
    private int status; //  integer($int32)
    private String error; //    string
    private Object message; //  string
    private String path; // string
}
