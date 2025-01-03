package ssafy.ddada.domain.gym.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.ddada.api.gym.response.GymDetailResponse;
import ssafy.ddada.api.gym.response.GymMatchesHistoryResponse;
import ssafy.ddada.api.gym.response.GymMatchesResponse;
import ssafy.ddada.common.exception.gym.GymAdminNotFoundException;
import ssafy.ddada.common.exception.gym.GymNotFoundException;
import ssafy.ddada.common.util.SecurityUtil;
import ssafy.ddada.domain.gym.entity.Gym;
import ssafy.ddada.domain.gym.repository.GymRepository;
import ssafy.ddada.domain.match.entity.Match;
import ssafy.ddada.domain.match.repository.MatchRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GymServiceImpl implements GymService {

    private final GymRepository gymRepository;
    private final MatchRepository matchRepository;

    @Override
    public GymDetailResponse getGymInfo() {
        log.info("[GymService] 체육관 정보 조회");
        Long gymAdminId = SecurityUtil.getLoginMemberId().orElseThrow(GymAdminNotFoundException::new);
        Gym gym = gymRepository.getGymsById(gymAdminId).orElseThrow(GymNotFoundException::new);
        return GymDetailResponse.from(gym);
    }

    @Override
    public GymMatchesResponse getGymMatches(LocalDate date) {
        log.info("[GymService] 체육관 경기 조회 >>>> 날짜: {}", date);
        Long gymAdminId = SecurityUtil.getLoginMemberId().orElseThrow(GymAdminNotFoundException::new);
        List<Match> matches = matchRepository.getMatchesByGymIdAndDate(gymAdminId, date);
        return GymMatchesResponse.from(matches);
    }

    @Override
    public GymMatchesHistoryResponse getGymMatchesHistory() {
        log.info("[GymService] 체육관 최근 경기 현황 조회");
        Long gymAdminId = SecurityUtil.getLoginMemberId().orElseThrow(GymAdminNotFoundException::new);
        Map<LocalDate, Integer> history = new HashMap<>();
        LocalDate aWeekAgo = LocalDate.now().minusWeeks(1);

        for (LocalDate date = aWeekAgo; date.isBefore(LocalDate.now()); date = date.plusDays(1)){
            int count = matchRepository.countByGymIdAndMatchDate(gymAdminId, date);
            history.put(date, count);
        }
        return GymMatchesHistoryResponse.of(history);
    }

}
