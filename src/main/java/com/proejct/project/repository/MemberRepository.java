package com.proejct.project.repository;

import com.proejct.project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Dao DB Layer 접근자
//Repository sql 매퍼라고 보면될듯?
//JpaRepository<Entity클래스, pk타입> 상속시, CRUD 메소드가 자동으로 생성
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String userEmail);
}
