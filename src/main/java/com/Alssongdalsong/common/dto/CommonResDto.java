package com.Alssongdalsong.common.dto;

import com.Alssongdalsong.common.domain.CommonMsg;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
public class CommonResDto {
    private int status_code;
    private String Status_message;
    private Object result;

    public CommonResDto(HttpStatus httpStatus, String message, Object result){
        this.status_code = httpStatus.value();
        this.Status_message = message;
        this.result = result;
    }

    public CommonResDto(HttpStatus httpStatus, CommonMsg commonMsg, Object result) {
        this.status_code = httpStatus.value();
        this.Status_message = commonMsg.getMessage();
        this.result = result;
    }
}
