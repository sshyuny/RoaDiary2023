package roadiary.behavior.member.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import roadiary.behavior.member.entity.UserEntity;
import roadiary.behavior.member.mapper.MemberMapper;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    MemberMapper memberMapper;
    
    @Override
    public UserEntity selectUserByUsingKakaoId(long kakaoId) {
        UserEntity userEntity = memberMapper.selectUserByUsingKakaoId(kakaoId);
        return userEntity;
    }

    @Override
    public int countKakaoIdUser(long kakaoId) {
        return memberMapper.countKakaoIdUser(kakaoId);
    }

    @Override
    public int insertKakaoUser(UserEntity userEntity) {
        return memberMapper.insertKakaoUser(userEntity);
    }
}
