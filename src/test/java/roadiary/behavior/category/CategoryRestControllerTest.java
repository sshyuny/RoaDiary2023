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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import roadiary.behavior.category.domain.dto.CategoryResDto;
import roadiary.behavior.category.domain.dto.SimpleReqDto;
import roadiary.behavior.category.domain.entity.CategoryEntity;
import roadiary.behavior.category.domain.entity.PriorityCategoryEntity;
import roadiary.behavior.category.repository.CategoryRepository;
import roadiary.behavior.member.domain.dto.MemberAuthorityDto;
import roadiary.behavior.member.service.authority.SessionAuthority;

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
    @Autowired
    CategoryRepository categoryRepository;

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

    @Test
    void saveCategories정상요청() throws Exception {
        
        //given
        SimpleReqDto simpleReqDto = new SimpleReqDto();
        simpleReqDto.setData("새로운카테고리");
        String jsonToString = objectMapper.writeValueAsString(simpleReqDto);
        
        //then
        mockMvc.perform(post("/api/category/priority").session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CategoryCommon.SUCCESS));

        Long newCategoryId =  categoryRepository.selectCategoryByContent("새로운카테고리");
        assertThat(newCategoryId).isEqualTo(15L);
    }

    @Test
    void saveCategories이미최대개수의카테고리저장된경우() throws Exception {
        
        //given
        SimpleReqDto simpleReqDto = new SimpleReqDto();
        simpleReqDto.setData("카테고리");
        String jsonToString = objectMapper.writeValueAsString(simpleReqDto);
        
        //when
        makePriorityFull();
        List<CategoryEntity> categoryEntities = categoryRepository.selectCategoryEntities(1L);

        //then
        mockMvc.perform(post("/api/category/priority").session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CategoryCommon.OVER));

        assertThat(categoryEntities.size()).isEqualTo(12);
    }

    @Test
    void saveCategories이미저장된카테고리를요청() throws Exception {

        //given
        SimpleReqDto simpleReqDto = new SimpleReqDto();
        simpleReqDto.setData("테스트2");
        String jsonToString = objectMapper.writeValueAsString(simpleReqDto);

        //then
        mockMvc.perform(post("/api/category/priority").session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CategoryCommon.DUPLI));
    }

    void makePriorityFull() {
        for (int i = 0; i < 9; i++) {
            categoryRepository.insertPriority(PriorityCategoryEntity.of(1L, 4 + i, 5 + i));
        }
    }
}
