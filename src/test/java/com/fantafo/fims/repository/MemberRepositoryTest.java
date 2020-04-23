package com.proejct.project.repository;

import com.proejct.project.entity.Member;
import com.proejct.project.entity.Membership;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MembershipRepository membershipRepository;

    @AfterEach
    public void cleanup() {
        memberRepository.deleteAll();
        membershipRepository.deleteAll();
    }

    @Test
    public void BaseTimeEntity_등록(){

        membershipRepository.save(Membership.builder()
                .grade((long)1)
                .name("free")
                .device_control_count(3)
                .build());

        List<Membership> memberships = membershipRepository.findAll();

        //given
        LocalDateTime now = LocalDateTime.now();
        memberRepository.save(Member.builder()
                .id("id")
                .password("password")
                .membership(memberships.get(0))
                .build());
        //when
        List<Member> postsList = memberRepository.findAll();

        //then
        Member member = postsList.get(0);
        assertThat(member.getCreatedDate().isAfter(now));
        //assertTrue(posts.getCreatedDate().isAfter(now));
        //assertTrue(posts.getModifiedDate().isAfter(now));
    }

}