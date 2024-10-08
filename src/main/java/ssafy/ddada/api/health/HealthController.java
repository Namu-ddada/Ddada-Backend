package ssafy.ddada.api.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.ddada.api.CommonResponse;
import ssafy.ddada.common.exception.errorcode.GlobalErrorCode;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/health")
@Tag(name = "Health", description = "서버 상태 확인")
public class HealthController {

    private final RedisTemplate<String, ?> redisTemplate;
    private final DataSource dataSource;

    @Operation(summary = "Redis 연결 확인", description = "Redis 서버와의 연결 상태를 확인합니다.")
    @GetMapping("/redis/check")
    public CommonResponse<String> checkRedisConnection() {
        try {
            String pingResponse = Objects.requireNonNull(redisTemplate.getConnectionFactory()).getClusterConnection().ping();
            if (!"PONG".equals(pingResponse)) {
                return CommonResponse.ok("Redis 연결 실패", null);
            }
            return CommonResponse.ok("Redis 연결 성공", null);
        } catch (Exception e) {
            log.error("Redis 연결 오류 발생", e);
            return CommonResponse.internalServerError(GlobalErrorCode.SERVER_ERROR);
        }
    }

    @Operation(summary = "PostgreSQL 연결 확인", description = "PostgreSQL 데이터베이스와의 연결 상태를 확인합니다.")
    @GetMapping("/postgres/check")
    public CommonResponse<String> checkPostgresConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (!connection.isValid(2)) {  // 2초 내에 연결 확인
                return CommonResponse.ok("PostgreSQL 연결 실패", null);
            }
            return CommonResponse.ok("PostgreSQL 연결 성공", null);
        } catch (Exception e) {
            log.error("PostgreSQL 연결 오류 발생", e);
            return CommonResponse.internalServerError(GlobalErrorCode.SERVER_ERROR);
        }
    }

    @Operation(summary = "서버 상태 확인", description = "서버 상태를 확인합니다.")
    @GetMapping("/ping")
    public CommonResponse<String> ping() {
        return CommonResponse.ok("pong");
    }
}
