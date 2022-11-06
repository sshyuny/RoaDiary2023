package roadiary.behavior.member.entity;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import roadiary.behavior.member.dto.KakaoUserInfoResDto;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class UserEntity {
    
    private Long userId;
    private Long kakaoId;
    private LocalDateTime kakaoConnectedAt;
    private String email;
    private String nickname;
    private LocalDateTime recentilyVisited;
    private String registerStatus;

    public static UserEntity of(KakaoUserInfoResDto kakaoUserInfoResDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setKakaoId(kakaoUserInfoResDto.getId());
        userEntity.setKakaoConnectedAt(kakaoUserInfoResDto.getConnected_at());
        userEntity.setEmail(kakaoUserInfoResDto.getKakao_account().getEmail());
        userEntity.setRegisterStatus("normal");
        return userEntity;
    }

    public static UserEntity of(long id, LocalDateTime time, String email, String nickname) {
        UserEntity userEntity = new UserEntity();
        userEntity.setKakaoId(id);
        userEntity.setKakaoConnectedAt(time);
        userEntity.setEmail(email);
        userEntity.setRegisterStatus("normal");
        userEntity.setNickname(nickname);
        return userEntity;
    }

}
