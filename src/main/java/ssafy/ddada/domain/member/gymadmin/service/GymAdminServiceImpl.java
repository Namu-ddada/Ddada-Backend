package ssafy.ddada.domain.member.gymadmin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.ddada.common.exception.gym.GymAdminNotFoundException;
import ssafy.ddada.domain.member.gymadmin.entity.GymAdmin;
import ssafy.ddada.domain.member.gymadmin.repository.GymAdminRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GymAdminServiceImpl implements GymAdminService{

    private final GymAdminRepository gymAdminRepository;

    @Override
    @Transactional
    public GymAdmin settleAccount(Long gymAdminId, String account, Integer amount) {
        log.info("계좌 {}에 {}원을 지급했습니다.", account, amount);
        GymAdmin gymAdmin = gymAdminRepository.findByIdWithInfos(gymAdminId)
                .orElseThrow(GymAdminNotFoundException::new);

        gymAdmin.setCumulativeIncome(0);
        return gymAdminRepository.save(gymAdmin);
    }

}