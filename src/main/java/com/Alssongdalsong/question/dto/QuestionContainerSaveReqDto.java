package com.Alssongdalsong.question.dto;

import com.Alssongdalsong.category.domain.Category;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.question.domain.Question;
import com.Alssongdalsong.question.domain.QuestionNaire;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionContainerSaveReqDto {
//    QuestionNaire 입력 영역
    private String title;
    private String description;
    private Long categoryId;

    private List<QuestionSaveReqDto> questionSaveReqDtos;

    public QuestionNaire toEntity(Member member, Category category, String bgmPath, String bgImagePath, String pointClickSoundPath) {
        return QuestionNaire.builder()
                .member(member)
                .title(this.title)
                .description(this.description)
                .category(category)
                .bgmPath(bgmPath)
                .bgImagePath(bgImagePath)
                .pointClickSoundPath(pointClickSoundPath)
                .build();
    }

//    Question 입력 영역
}
