package roadiary.behavior.member.repository;

import roadiary.behavior.member.entity.UserEntity;

public interface MemberRepository {
    
    public UserEntity selectUserByUsingKakaoId(long kakaoId);

    public int countKakaoIdUser(long kakaoId);

    public int insertKakaoUser(UserEntity userEntity);

}
