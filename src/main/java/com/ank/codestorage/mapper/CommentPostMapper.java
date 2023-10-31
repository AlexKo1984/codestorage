package com.ank.codestorage.mapper;

import com.ank.codestorage.dto.*;
import com.ank.codestorage.model.CommentPost;
import com.ank.codestorage.model.LangCode;
import com.ank.codestorage.model.Post;
import com.ank.codestorage.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentPostMapper {
    private final ModelMapper modelMapper;

//    public List<CommentPostDto> mapToCommentPostDto(List<CommentPost> comments) {
//        List<CommentPostDto> result = new ArrayList<>();
//
//        for (CommentPost commentPost : comments) {
//            result.add(mapToCommentPostDto(commentPost));
//        }
//
//        return result;
//    }
    public CommentPostDto mapToCommentPostDto(CommentPost commentPost) {
        return CommentPostDto.builder()
                .id(commentPost.getId())
                .dateCreate(commentPost.getDateCreate())
                .userLogin(commentPost.getUser().getLogin())
                .userId(commentPost.getUser().getId())
                .postId(commentPost.getPost().getId())
                .text(commentPost.getText())
                .build();
    }

//    public CommentPost mapToCommentPost(CommentPostDto commentPostDto) {
//        return modelMapper.map(commentPostDto, CommentPost.class);
//    }
//    public CommentPost mapToCommentPost(InputCommentPostDto commentPostDto) {
//        return modelMapper.map(commentPostDto, CommentPost.class);
//    }

    public PageDto<CommentPostDto> mapToPageCommentPostDto(Page<CommentPost> page) {
        List<CommentPostDto> content = new ArrayList<>();

        for (CommentPost commentPost : page.getContent()) {
            content.add(mapToCommentPostDto(commentPost));
        }

        return new PageDto<>(page.getTotalElements(), content, page.getNumber(), page.getSize(), page.getTotalPages());
    }
}
