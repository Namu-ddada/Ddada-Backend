package ssafy.ddada.api.match.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ssafy.ddada.domain.match.command.MatchCreateCommand;
import ssafy.ddada.domain.match.entity.MatchType;
import ssafy.ddada.domain.match.entity.RankType;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "경기 생성 요청 DTO")
public record MatchCreateRequest(
        @Schema(description = "코트 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "코트 ID는 필수입니다.")
        Long courtId,

        @Schema(description = "랭크 타입", example = "RANK", requiredMode = Schema.RequiredMode.REQUIRED)
        RankType rankType,

        @Schema(description = "경기 타입", example = "FEMALE_DOUBLE", requiredMode = Schema.RequiredMode.REQUIRED)
        MatchType matchType,

        @Schema(description = "경기 일자", example = "2024-09-06", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDate date,

        @Schema(description = "경기 시간", example = "10:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalTime time
) {
    public MatchCreateCommand toCommand(){
        return new MatchCreateCommand(courtId, rankType, matchType, date, time);
    }
}
