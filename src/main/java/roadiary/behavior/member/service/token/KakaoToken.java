package roadiary.behavior.member.service.token;

import org.springframework.util.MultiValueMap;

/**
 * 구현체는 git ignore 됩니다.
 */
public interface KakaoToken {
    
    public MultiValueMap<String, String> newKakaoToken(String code);

    public String getKakaoLoginReqUrl();

}
