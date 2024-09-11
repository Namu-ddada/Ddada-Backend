package ssafy.ddada.api.match.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ssafy.ddada.domain.match.command.MatchCreateCommand;
import ssafy.ddada.domain.match.entity.MatchTime;
import ssafy.ddada.domain.match.entity.MatchType;

import java.time.LocalDate;

@Schema(description = "경기 생성 요청 DTO")
public record MatchCreateRequest(
        @Schema(description = "시설 ID", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "시설 ID는 필수입니다.")
        Long court_id,

        @Schema(description = "단식 여부", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
        MatchType matchType,

        @Schema(description = "경기 일자", example = "2024-09-06")
        LocalDate matchDate,

        @Schema(description = "경기 시간", example = "10")
        MatchTime matchTime
) {
    public MatchCreateCommand toCommand(){
        return new MatchCreateCommand(court_id, matchType, matchDate, matchTime);
    }
}
