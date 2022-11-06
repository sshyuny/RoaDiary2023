package roadiary.behavior.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import roadiary.behavior.member.entity.UserEntity;

@Mapper
public interface MemberMapper {
    
    public UserEntity selectUserByUsingKakaoId(long kakaoId);

    public int countKakaoIdUser(long kakaoId);

    public int insertKakaoUser(UserEntity userEntity);
    
}
