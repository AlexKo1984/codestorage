package com.ank.codestorage.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class User extends AbstractEntity {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp="\\S+")
    private String login;
    private String name;
    @Past
    private LocalDate birthday;

    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private final Set<Long> friends = new HashSet<>();

    public void addFriend(Long id) {
        friends.add(id);
    }

    public void removeFriend(Long id) {
        friends.remove(id);
    }

    public List<Long> getFiends() {
        return new ArrayList<>(friends);
    }

    public boolean containsFriend(Long id){
        return friends.contains(id);
    }
}