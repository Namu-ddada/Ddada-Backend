package ssafy.ddada.api.member.player.response;

import io.swagger.v3.oas.annotations.media.Schema;
import ssafy.ddada.domain.member.common.Gender;

public record PlayerDetailResponse(
        @Schema(description = "프로필 이미지 경로", example = "https://my-bucket.s3.amazonaws.com/sample-image.jpg\n")
        String profilePreSignedUrl,

        @Schema(description = "닉네임", example = "쿠잉비")
        String nickname,

        @Schema(description = "레이팅", example = "25")
        Integer rating
) {
    public static PlayerDetailResponse of(String profileImageBase64, String nickname, Integer rating) {
        return new PlayerDetailResponse(profileImageBase64, nickname, rating);
    }
}