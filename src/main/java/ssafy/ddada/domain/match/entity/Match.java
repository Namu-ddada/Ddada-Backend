package ssafy.ddada.domain.match.entity;

import jakarta.persistence.*;
import lombok.*;
import ssafy.ddada.domain.court.entity.Court;
import ssafy.ddada.domain.member.common.Gender;
import ssafy.ddada.domain.member.manager.entity.Manager;
import ssafy.ddada.domain.member.player.entity.Player;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match extends BaseMatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "court_id", nullable = false)
    private Court court;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team2_id")
    private Team team2;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    private Integer winnerTeamNumber;

    private Integer team1SetScore;

    private Integer team2SetScore;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RankType rankType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @Column(nullable = false)
    private LocalDate matchDate;

    private LocalTime matchTime;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<Set> sets = new ArrayList<>();

    public List<String> getTeamGender(Team team) {
        List<String> teamGender = new ArrayList<>();

        Player player1 = team.getPlayer1();
        Player player2 = team.getPlayer2();

        // First, add male players
        if (player1 != null && player1.getGender() == Gender.MALE) {
            teamGender.add(player1.getGender().name());
        }
        if (player2 != null && player2.getGender() == Gender.MALE) {
            teamGender.add(player2.getGender().name());
        }

        // Then, add female players
        if (player1 != null && player1.getGender() == Gender.FEMALE) {
            teamGender.add(player1.getGender().name());
        }
        if (player2 != null && player2.getGender() == Gender.FEMALE) {
            teamGender.add(player2.getGender().name());
        }

        // If a player is not reserved, add "notReserved"
        if (player1 == null) {
            teamGender.add("notReserved");
        }
        if (player2 == null) {
            teamGender.add("notReserved");
        }

        return teamGender;
    }
}
