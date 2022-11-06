package roadiary.behavior.member.repository;

import roadiary.behavior.member.entity.UserEntity;

public interface MemberRepository {
    
    public UserEntity selectUserByUsingKakaoId(long kakaoId);
    public UserEntity selectUserByUsingUserId(long userId);

    public int countKakaoIdUser(long kakaoId);

    public int insertKakaoUser(UserEntity userEntity);

    public void insertPriorityForNewUser(long userId);
    
    public Integer withdrawalUser(long userId);

}
