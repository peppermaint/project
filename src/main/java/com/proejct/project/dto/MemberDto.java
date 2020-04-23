package com.proejct.project.dto;

import com.proejct.project.entity.Member;
import com.proejct.project.entity.Membership;
import com.proejct.project.util.ExAssert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private String email;
    private String id;
    private String password;
    private Membership membership;

    @Builder
    public MemberDto(String email, String id, String password, Membership membership) {
        ExAssert.notNull("Member email, id, password, membership must not be null", email, id, password,membership);
        this.email = email;
        this.id = id;
        this.password = password;
        this.membership = membership;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .id(id)
                .password(password)
                .build();
    }
}
