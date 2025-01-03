package ssafy.ddada.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ssafy.ddada.common.exception.errorcode.BaseErrorCode;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final BaseErrorCode errorCode;
}
