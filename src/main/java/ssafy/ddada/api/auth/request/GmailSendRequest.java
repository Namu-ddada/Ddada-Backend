package ssafy.ddada.api.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import ssafy.ddada.domain.auth.command.GmailSendCommand;

public record GmailSendRequest (
    @Schema(description = "이메일", example = "example@gmail.com)", format = "email")
    String email
) {
    public GmailSendCommand toCommand() {return new GmailSendCommand(email);}
}