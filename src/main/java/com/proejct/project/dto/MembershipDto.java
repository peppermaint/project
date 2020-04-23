package com.proejct.project.dto;

import com.proejct.project.entity.Member;
import com.proejct.project.entity.Membership;
import com.proejct.project.util.ExAssert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Controller에서 @RequestBody로 외부에서 데이터를 받는 경우엔 기본생성자 + set 메소드를 통해서만 값이 할당
//절대 테이블과 매핑되는 Entity 클래스를 Request/Response 클래스로 사용 금지
//Request/Response용 TO는 View를 위한 클래스라 자주 변경이 필요.
//View Layer와 DB Layer의 역할 분리
@Getter
@Setter
@NoArgsConstructor
public class MembershipDto {

    private Long grade;
    private int device_control_count;
    private String name;

    @Builder
    public MembershipDto(Long grade, String name, int device_control_count){
        ExAssert.notNull("Membership grade, name, device_control_count must not be null", grade, name, device_control_count);

        this.grade = grade;
        this.name = name;
        this.device_control_count = device_control_count;
    }

    public Membership toEntity(){
        return Membership.builder()
                .grade(grade)
                .device_control_count(device_control_count)
                .name(name)
                .build();
    }
}
