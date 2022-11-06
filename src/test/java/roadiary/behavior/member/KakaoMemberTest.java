package roadiary.behavior.member;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.member.entity.UserEntity;
import roadiary.behavior.member.repository.MemberRepository;
import roadiary.behavior.member.service.MemberService;

@ActiveProfiles("local")
@SpringBootTest
public class KakaoMemberTest {
    
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("카카오 로그인으로 회원 가입")
    @Transactional
    void 카카오로그인회원가입() {
        //given
        long kakaoId = 1111L;
        LocalDateTime kakaoConnectedAt = LocalDateTime.of(2022, 11, 06, 14, 39);
        String email = "test@test.com";
        String nickname = "usertest";
        UserEntity userEntity1 = UserEntity.of(kakaoId, kakaoConnectedAt, email, nickname);

        //when
        memberRepository.insertKakaoUser(userEntity1);
        UserEntity userEntity2 = memberRepository.selectUserByUsingKakaoId(kakaoId);
        long userId = userEntity2.getUserId();
        int countNum = memberRepository.countKakaoIdUser(kakaoId);

        //then
        assertThat(userId).isNotEqualTo(0L);
        assertThat(countNum).isEqualTo(1);
        assertThat(userEntity2.getEmail()).isEqualTo(email);
        assertThat(userEntity2.getKakaoConnectedAt()).isEqualTo(kakaoConnectedAt);
        assertThat(userEntity2.getNickname()).isEqualTo(nickname);
    }

    
}
