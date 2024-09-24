package ssafy.ddada.domain.auth.service;

import org.apache.http.auth.InvalidCredentialsException;
import ssafy.ddada.api.auth.request.SmsRequest;
import ssafy.ddada.api.auth.response.MemberTypeResponse;
import ssafy.ddada.config.auth.AuthResponse;
import ssafy.ddada.config.auth.TokenRefreshRequest;
import ssafy.ddada.domain.auth.command.GmailSendCommand;
import ssafy.ddada.domain.auth.command.LoginCommand;
import ssafy.ddada.domain.auth.command.LogoutCommand;
import ssafy.ddada.domain.auth.command.VerifyCommand;

public interface AuthService {
    AuthResponse login(LoginCommand command);
    AuthResponse refresh(TokenRefreshRequest refreshToken);
    void logout();
    void sendSms(SmsRequest smsRequest);
    Boolean verifyCertificationCode(VerifyCommand command);
    MemberTypeResponse getMemberType();
    void sendEmail(GmailSendCommand gmailSendCommand);
}