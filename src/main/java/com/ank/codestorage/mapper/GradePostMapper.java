package com.ank.codestorage.mapper;

import com.ank.codestorage.dto.CommentPostDto;
import com.ank.codestorage.dto.GradePostDto;
import com.ank.codestorage.dto.InputCommentPostDto;
import com.ank.codestorage.model.CommentPost;
import com.ank.codestorage.model.GradePost;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GradePostMapper {
    //private final ModelMapper modelMapper;

    public GradePostDto mapToGradePostDto(GradePost gradePost) {
        //return modelMapper.map(gradePost, GradePostDto.class);
        return new GradePostDto(gradePost.getUser().getId(), gradePost.getPost().getId(), gradePost.getValue());
    }
}
