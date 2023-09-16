package soongmile.soongmileback.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {

    private final boolean success;
    private final int code;
    private final String message;
    private final T result;

    public static <T> ResponseDto<T> success() {
        return new ResponseDto<>(true, 200, null, null);
    }

    public static <T> ResponseDto<T> success(String message) {
        return new ResponseDto<>(true, 200, message, null);
    }

    public static <T> ResponseDto<T> success(String message, T result) {
        return new ResponseDto<>(true, 200, message, result);
    }

    public static <T> ResponseDto<T> fail(HttpStatus httpStatus, String message) {
        return new ResponseDto<>(false, httpStatus.value(), message, null);
    }

    public static <T> ResponseDto<T> fail(HttpStatus httpStatus, String message, T result) {
        return new ResponseDto<>(false, httpStatus.value(), message, result);
    }
}
