package com.proejct.project.entity;

import com.proejct.project.util.ExAssert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Membership {

    @Id
    private Long grade;

    //멤버쉽 등급 이름
    @Column(unique = true)
    private String name;

    //제어 가능한 기기 수
    private int device_control_count;

    @Builder
    public Membership(Long grade, String name, int device_control_count){

        ExAssert.notNull("Membership grade, name, device_control_count must not be null", grade, name, device_control_count);

        this.grade = grade;
        this.name = name;
        this.device_control_count = device_control_count;
    }
}
