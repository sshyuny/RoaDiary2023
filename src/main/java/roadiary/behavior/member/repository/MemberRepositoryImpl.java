package roadiary.behavior.member.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import roadiary.behavior.member.domain.entity.UserEntity;
import roadiary.behavior.member.repository.mapper.MemberMapper;

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
    public UserEntity selectUserByUsingUserId(long userId) {
        return memberMapper.selectUserByUsingUserId(userId);
    }

    @Override
    public int countKakaoIdUser(long kakaoId) {
        return memberMapper.countKakaoIdUser(kakaoId);
    }

    @Override
    public int insertKakaoUser(UserEntity userEntity) {
        return memberMapper.insertKakaoUser(userEntity);
    }

    @Override
    public void insertPriorityForNewUser(long userId) {
        memberMapper.insertPriorityForNewUser(userId);
    }

    @Override
    public Integer withdrawalUser(long userId) {
        return memberMapper.withdrawalUser(userId);
    }

}
