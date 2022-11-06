package roadiary.behavior.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.member.entity.UserEntity;
import roadiary.behavior.member.repository.MemberRepository;

@ActiveProfiles("local")
@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;
    
    @Test
    @DisplayName("탈퇴 진행")
    @Transactional
    void 탈퇴진행() {
        //given
        long userId = 1;
        UserEntity userEntityBefore = memberRepository.selectUserByUsingUserId(userId);
        String registerStatusBefore = userEntityBefore.getRegisterStatus();

        //when
        int withdrawedNum = memberRepository.withdrawalUser(userId);
        UserEntity userEntityAfter = memberRepository.selectUserByUsingUserId(userId);
        String registerStatusAfter = userEntityAfter.getRegisterStatus();

        //then
        assertThat(withdrawedNum).isEqualTo(1);
        assertThat(registerStatusBefore).isEqualTo("normal");
        assertThat(registerStatusAfter).isEqualTo("withdrawal");
    }
}
