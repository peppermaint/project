package com.proejct.project.entity;

import com.proejct.project.util.ExAssert;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@NoArgsConstructor 기본 생성자 자동 추가
//@Getter 클래스내 모든 필드의 Getter 메소드를 자동 생성
//@Entity 테이블과 링크될 클래스 알림. SalesManager.java -> sales_manager table 매칭
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {
    //@Id PK필드
    //@GeneratedValue mysql의 auto_increment 기능
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    private String id;

    //@Column 생략가능
    @Column(length = 20, nullable = false)
    private String password;

    @ManyToOne
    private Membership membership;

    //@Builder 해당 클래스의 빌더패턴 클래스를 생성
    //일반 생성자보다 채워야할 필드가 무엇인지 명확히 지정할 수 있음
    //member.builder().build()시 모든 값이 null로 생성됨.
    //db칼럼이 not null이라 문제가 발생
    @Builder
    public Member(String email, String id, String password, Membership membership){

        ExAssert.notNull("Member email, id, password, membership must not be null", email, id, password,membership);

        this.email = email;
        this.id = id;
        this.password = password;
        this.membership = membership;
    }
}
