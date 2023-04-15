package roadiary.behavior.member.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import roadiary.behavior.member.domain.entity.UserEntity;

@Mapper
public interface MemberMapper {
    
    public UserEntity selectUserByUsingKakaoId(long kakaoId);
    public UserEntity selectUserByUsingUserId(long userId);

    public int countKakaoIdUser(long kakaoId);

    public int insertKakaoUser(UserEntity userEntity);

    public void insertPriorityForNewUser(long userId);

    public Integer withdrawalUser(long userId);
    
}
