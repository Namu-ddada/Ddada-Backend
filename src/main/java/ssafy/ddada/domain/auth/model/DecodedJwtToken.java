package ssafy.ddada.domain.auth.model;

public record DecodedJwtToken(
        Long memberId,
        String role,
        String type
) {
}