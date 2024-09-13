package ssafy.ddada.common.exception;

import ssafy.ddada.common.exception.errorcode.PlayerErrorCode;

public class KakaoMailPlayerNotFoundException extends BaseException {
    public KakaoMailPlayerNotFoundException() {
        super(PlayerErrorCode.KAKAO_MAIL_PLAYER_NOT_FOUND);
    }
}
