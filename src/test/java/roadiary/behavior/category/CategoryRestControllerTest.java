package roadiary.behavior.category;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.member.authority.SessionAuthority;
import roadiary.behavior.member.dto.MemberAuthorityDto;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CategoryRestControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    @Autowired
    SessionAuthority sessionAuthority;

    ObjectMapper objectMapper;
    MockHttpSession session;

    @BeforeAll
    void init() throws Exception {

        //의존주입
        objectMapper = new ObjectMapper();
        session = new MockHttpSession();
        
        //로그인 세션 생성
        MemberAuthorityDto memberAuthorityDto = MemberAuthorityDto.of(1L, "test");
        sessionAuthority.makeLoginStatus(session, memberAuthorityDto);

    }
    
    @Test
    void getCategories테스트() throws Exception {
        
        MvcResult mvcResult = mockMvc.perform(get("/api/category/priority").session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        List<CategoryResDto> categoryResDtos = objectMapper.readValue(json, new TypeReference<List<CategoryResDto>>() {});

        assertThat(categoryResDtos.size()).isEqualTo(3);
        assertThat(categoryResDtos.get(0).getId()).isEqualTo(2L);
        assertThat(categoryResDtos.get(0).getContent()).isEqualTo("테스트2");
        assertThat(categoryResDtos.get(2).getId()).isEqualTo(4L);
        assertThat(categoryResDtos.get(2).getContent()).isEqualTo("테스트4");
    }
}
