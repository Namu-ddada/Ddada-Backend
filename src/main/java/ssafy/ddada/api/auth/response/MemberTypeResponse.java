package ssafy.ddada.api.auth.response;

import io.swagger.v3.oas.annotations.media.Schema;
import ssafy.ddada.domain.member.common.MemberRole;

@Schema(description = "회원 타입 응답 DTO")
public record MemberTypeResponse (
    @Schema(description = "회원 타입", example = "manager")
    String memberType
) {
    public static MemberTypeResponse of(MemberRole memberRole) {
        return new MemberTypeResponse(memberRole.name());
    }
}
