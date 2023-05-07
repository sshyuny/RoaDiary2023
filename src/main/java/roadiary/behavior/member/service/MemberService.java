package roadiary.behavior.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.member.domain.dto.KakaoTokenResDto;
import roadiary.behavior.member.domain.dto.KakaoUserInfoResDto;
import roadiary.behavior.member.domain.dto.MemberAuthorityDto;
import roadiary.behavior.member.domain.entity.UserEntity;
import roadiary.behavior.member.repository.MemberRepository;
import roadiary.behavior.member.service.authority.Authority;
import roadiary.behavior.member.service.token.KakaoToken;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final Authority authority;
    private final MemberRepository memberRepository;
    private final KakaoToken kakaoToken;

    public void makeLoginSession(HttpSession session) {
        MemberAuthorityDto memberAuthorityDto = MemberAuthorityDto.of(1, "guest");
        authority.makeLoginSession(session, memberAuthorityDto);
    }
    public String makeLoginSession(HttpSession session, long kakaoId) {
        UserEntity userEntity = memberRepository.selectUserByUsingKakaoId(kakaoId);
        if(userEntity.getRegisterStatus().equals("withdrawal")) return "withdrawal";
        else {
            MemberAuthorityDto memberAuthorityDto = MemberAuthorityDto.of(userEntity.getUserId(), userEntity.getNickname());
            authority.makeLoginSession(session, memberAuthorityDto);
            return "success";
        }
    }

    public void destroyLoginSession(HttpSession session) {
        authority.destroyLoginSession(session);
    }

    public String getKakaoLoginReqUrl() {
        return kakaoToken.getKakaoLoginReqUrl();
    }

    public String getKaKaoAccessToken(String code) {

        // body 생성
        MultiValueMap<String, String> kakaoTokenMap = kakaoToken.newKakaoToken(code);

        // requestEntity 생성
        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
            .post(UriComponentsBuilder.fromUriString("https://kauth.kakao.com/oauth/token").build().toUri())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(kakaoTokenMap);

        // 카카오에 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoTokenResDto> response = restTemplate.exchange(requestEntity, KakaoTokenResDto.class);

        // reponse 사용
        KakaoTokenResDto kakaoTokenResDto = response.getBody();
        return kakaoTokenResDto.getAccess_token();
    }
    public KakaoUserInfoResDto getUserInfoByToken(String accessToken) {

        MultiValueMap<String, Object> kakaoUserInfoReqDto = new LinkedMultiValueMap<>();
        kakaoUserInfoReqDto.add("property_keys", "[\"kakao_account.email\"]");

        // requestEntity 생성
        RequestEntity<Object> requestEntity = RequestEntity
            .post(UriComponentsBuilder.fromUriString("https://kapi.kakao.com/v2/user/me").build().toUri())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .header("Authorization", "Bearer " + accessToken)
            .body(kakaoUserInfoReqDto);
        
        // 카카오에 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoUserInfoResDto> response = restTemplate.exchange(requestEntity, KakaoUserInfoResDto.class);

        // reponse 사용
        KakaoUserInfoResDto kakaoUserInfoResDto = response.getBody();
        return kakaoUserInfoResDto;
    }
    public boolean isItRegisteredMember(Long kakaoId) {
        int countNum = memberRepository.countKakaoIdUser(kakaoId);
        if (countNum == 1) return true;
        else return false;
    }
    public void addKakaoUser(KakaoUserInfoResDto kakaoUserInfoResDto) {
        UserEntity userEntity = UserEntity.of(kakaoUserInfoResDto);
        userEntity.setNickname("user");
        memberRepository.insertKakaoUser(userEntity);  // 여기서 userId 들어감
        memberRepository.insertPriorityForNewUser(userEntity.getUserId());
    }

    public Integer withdrawalUser(long userId) {
        return memberRepository.withdrawalUser(userId);
    }
}
