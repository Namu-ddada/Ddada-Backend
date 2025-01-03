package ssafy.ddada.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ssafy.ddada.api.CommonResponse;
import ssafy.ddada.common.exception.gym.CourtNotFoundException;
import ssafy.ddada.common.exception.gym.GymAdminNotFoundException;
import ssafy.ddada.common.exception.gym.GymNotFoundException;
import ssafy.ddada.common.exception.gym.InvalidRegionException;

@Slf4j
@RestControllerAdvice
public class GymExceptionHandler {

    @ExceptionHandler(InvalidRegionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> handleInvalidRegionException(InvalidRegionException e) {
        log.error("InvalidRegionException occurs", e);
        return CommonResponse.badRequest(e.getErrorCode());
    }

    @ExceptionHandler(GymNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse<?> handleGymNotFoundException(GymNotFoundException e) {
        log.error("GymNotFoundException occurs", e);
        return CommonResponse.notFound(e.getErrorCode());
    }

    @ExceptionHandler(GymAdminNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse<?> handleGymAdminNotFoundException(GymAdminNotFoundException e) {
        log.error("GymAdminNotFoundException occurs", e);
        return CommonResponse.notFound(e.getErrorCode());
    }

    @ExceptionHandler(CourtNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse<?> handleCourtNotFoundException(CourtNotFoundException e) {
        log.error("CourtNotFoundException occurs", e);
        return CommonResponse.notFound(e.getErrorCode());
    }

}
