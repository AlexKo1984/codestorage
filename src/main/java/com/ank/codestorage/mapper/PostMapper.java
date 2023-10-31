package com.ank.codestorage.mapper;

import com.ank.codestorage.dto.InputPostDto;
import com.ank.codestorage.dto.PageDto;
import com.ank.codestorage.dto.PostDto;
import com.ank.codestorage.dto.UserDto;
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
public class PostMapper {
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final LangCodeMapper langCodeMapper;

    public List<PostDto> mapToPostDto(Iterable<Post> posts) {
        List<PostDto> result = new ArrayList<>();

        for (Post post : posts) {
            result.add(mapToPostDto(post));
        }

        return result;
    }

    public PostDto mapToPostDto(Post post) {
        PostDto result = modelMapper.map(post, PostDto.class);
        result.setOwner(userMapper.mapToShortUserDto(post.getOwner()));
        result.setLangCode(langCodeMapper.mapToLangCodeDto(post.getLangCode()));

        return result;
    }

    public Post mapToPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
    public Post mapToPost(InputPostDto postDto) {
        Post result = modelMapper.map(postDto, Post.class);

        return result;
    }
}
