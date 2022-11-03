package roadiary.behavior.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KakaoTokenResDto {
    
    private String token_type;
    private String access_token;
    private String refresh_token;
    private Long expires_in;
    private Long refresh_token_expires_in;
    private String scope;

}
