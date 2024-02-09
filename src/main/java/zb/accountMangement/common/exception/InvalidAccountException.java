package zb.accountMangement.common.exception;

import lombok.Getter;
import zb.accountMangement.common.type.ErrorCode;

@Getter
public class InvalidAccountException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidAccountException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = getErrorCode();
    }
}
