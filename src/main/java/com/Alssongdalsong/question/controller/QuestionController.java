package com.Alssongdalsong.question.controller;

import com.Alssongdalsong.question.domain.QuestionNaire;
import com.Alssongdalsong.question.dto.QuestionDetResDto;
import com.Alssongdalsong.question.dto.QuestionListResDto;
import com.Alssongdalsong.question.dto.QuestionNaireListResDto;
import com.Alssongdalsong.question.dto.QuestionContainerSaveReqDto;
import com.Alssongdalsong.question.service.QuestionService;
import com.Alssongdalsong.common.domain.CommonMsg;
import com.Alssongdalsong.common.dto.CommonResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    //    문제 생성
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    @PostMapping("/question/create")
    public ResponseEntity<?> questionCreate(@RequestPart(value="container") QuestionContainerSaveReqDto dto,
                                            @RequestPart(value="bgmPath") MultipartFile bgmPath,
                                            @RequestPart(value="bgImagePath") MultipartFile bgImagePath,
                                            @RequestPart(value="pointClickSoundPath") MultipartFile pointClickSoundPath
                                            )
    {
        QuestionNaire questionNaire = questionService.questionCreate(dto,bgmPath,bgImagePath,pointClickSoundPath);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, CommonMsg.QUESTION_CREATED, questionNaire.getId());
        return new ResponseEntity<>(commonResDto,HttpStatus.CREATED);
    }

    //    문제 그룹 리스트
    @GetMapping("/question/all")
    public ResponseEntity<?> questionNaireAllList(Pageable pageable){
        Page<QuestionNaireListResDto> questionList = questionService.questionNaireAllList(pageable);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, CommonMsg.LIST_RETURN, questionList);
        return new ResponseEntity<>(commonResDto,HttpStatus.OK);
    }

    //    내가 만든 문제 그룹 리스트
    @GetMapping("/question/list")
    public ResponseEntity<?> questionNaireList(Pageable pageable){
        Page<QuestionNaireListResDto> questionList = questionService.questionNaireList(pageable);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, CommonMsg.LIST_RETURN, questionList);
        return new ResponseEntity<>(commonResDto,HttpStatus.OK);
    }

    //    문제 그룹별 문제 리스트 보기
    @GetMapping("/question/{id}/list")
    public ResponseEntity<?> questionList(Pageable pageable, @PathVariable Long id){
        Page<QuestionListResDto> questionList = questionService.questionList(pageable, id);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, CommonMsg.LIST_RETURN, questionList);
        return new ResponseEntity<>(commonResDto,HttpStatus.OK);
    }

    //    문제 상세
    @GetMapping("/question/detail/{id}")
    public ResponseEntity<?> questionList(@PathVariable Long id){
        QuestionDetResDto dto = questionService.questionDetail(id);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, CommonMsg.DETAIL_RETURN, dto);
        return new ResponseEntity<>(commonResDto,HttpStatus.OK);
    }
}
