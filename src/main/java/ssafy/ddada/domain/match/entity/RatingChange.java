package ssafy.ddada.domain.match.entity;

import jakarta.persistence.*;
import lombok.*;
import ssafy.ddada.domain.member.player.entity.Player;

@Getter
@Builder
@Entity
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RatingChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_change_id")
    private Long id;

    @Setter
    private Integer ratingChange;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;


    public static RatingChange createRatingChange() {
        return new RatingChange();
    }
}
