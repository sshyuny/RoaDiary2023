package roadiary.behavior.member.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KakaoUserInfoResDto {
    
    private Long id;
    private String has_signed_up;
    private LocalDateTime connected_at;
    private String synched_at;
    private String properties;
    private KakaoAccount kakao_account;

}
