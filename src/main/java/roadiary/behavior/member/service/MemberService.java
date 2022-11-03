package roadiary.behavior.member.service;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import roadiary.behavior.member.authority.Authority;
import roadiary.behavior.member.dto.KakaoTokenResDto;
import roadiary.behavior.member.dto.KakaoUserInfoResDto;
import roadiary.behavior.member.dto.MemberAuthorityDto;

@Service
public class MemberService {

    private Authority authority;

    public MemberService(Authority authority) {
        this.authority = authority;
    }
    
    public boolean isItExistMember() {
        // 이후 수정
        return true;
    }

    public void makeLoginStatus(HttpServletRequest request) {
        // 이후 수정
        MemberAuthorityDto memberAuthorityDto = new MemberAuthorityDto("temp-user");
        authority.makeLoginStatus(request, memberAuthorityDto);
    }

    public void destroyLoginStatus(HttpServletRequest request) {
        // 이후 수정
        authority.destroyLoginStatus(request);
    }

    public String getKaKaoAccessToken(String code) {

        // body 생성(KakaoTokenReqMap는 git ignore됩니다.)
        MultiValueMap<String, String> kakaoTokenReqMap = KakaoTokenReqMap.of(code);

        // requestEntity 생성
        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
            .post(UriComponentsBuilder.fromUriString("https://kauth.kakao.com/oauth/token").build().toUri())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(kakaoTokenReqMap);

        // 카카오에 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoTokenResDto> response = restTemplate.exchange(requestEntity, KakaoTokenResDto.class);

        // reponse 사용
        KakaoTokenResDto kakaoTokenResDto = response.getBody();
        return kakaoTokenResDto.getAccess_token();
    }
    public KakaoUserInfoResDto getUserInfoByToken(String accessToken) {

        // requestEntity 생성
        RequestEntity<Object> requestEntity = RequestEntity
            .post(UriComponentsBuilder.fromUriString("https://kapi.kakao.com/v2/user/me").build().toUri())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .header("Authorization", "Bearer " + accessToken)
            .body(null);
        
        // 카카오에 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoUserInfoResDto> response = restTemplate.exchange(requestEntity, KakaoUserInfoResDto.class);

        // reponse 사용
        KakaoUserInfoResDto kakaoUserInfoResDto = response.getBody();
        return kakaoUserInfoResDto;
    }
}
