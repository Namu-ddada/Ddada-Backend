package ssafy.ddada.api.match.request;

import io.swagger.v3.oas.annotations.media.Schema;
import ssafy.ddada.domain.match.command.MatchResultCommand;
import ssafy.ddada.domain.match.entity.EarnedType;
import ssafy.ddada.domain.match.entity.MissedType;

import java.util.List;

public record MatchResultRequest(
        @Schema(description = "승리 팀 번호")
        Integer winnerTeamNumber,
        @Schema(description = "팀1 세트 점수")
        Integer team1SetScore,
        @Schema(description = "팀2 세트 점수")
        Integer team2SetScore,
        @Schema(description = "세트 정보")
        List<SetResultRequest> sets
) {
    public record SetResultRequest(
            @Schema(description = "세트 번호")
            Integer setNumber,
            @Schema(description = "세트 승리 팀 번호")
            Integer setWinnerTeamNumber,
            @Schema(description = "팀1 점수")
            Integer team1Score,
            @Schema(description = "팀2 점수")
            Integer team2Score,
            @Schema(description = "득점 정보")
            List<ScoreResultRequest> scores
    ) {
        public record ScoreResultRequest(
                @Schema(description = "득점 번호")
                Integer scoreNumber,
                @Schema(description = "득점 선수", nullable = true)
                Integer earnedPlayer,
                @Schema(description = "실점 선수1", nullable = true)
                Integer missedPlayer1,
                @Schema(description = "실점 선수2", nullable = true)
                Integer missedPlayer2,
                @Schema(description = "득점 방식", nullable = true)
                EarnedType earnedType,
                @Schema(description = "실점 방식", nullable = true)
                MissedType missedType
        ) {
            public MatchResultCommand.SetResultCommand.ScoreResultCommand toCommand() {
                return new MatchResultCommand.SetResultCommand.ScoreResultCommand(
                        scoreNumber,
                        earnedPlayer,
                        missedPlayer1,
                        missedPlayer2,
                        earnedType,
                        missedType
                );
            }
        }

        public MatchResultCommand.SetResultCommand toCommand() {
            return new MatchResultCommand.SetResultCommand(
                    setNumber,
                    setWinnerTeamNumber,
                    team1Score,
                    team2Score,
                    scores.stream()
                            .map(ScoreResultRequest::toCommand)
                            .toList()
            );
        }
    }

    public MatchResultCommand toCommand(){
        return new MatchResultCommand(
                winnerTeamNumber,
                team1SetScore,
                team2SetScore,
                sets.stream()
                        .map(SetResultRequest::toCommand)
                        .toList()
        );
    }
}