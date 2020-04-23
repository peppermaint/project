package com.proejct.project.service;

import com.proejct.project.dto.MembershipDto;
import com.proejct.project.repository.MembershipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class MembershipService {
    private MembershipRepository membershipRepository;

    @Transactional
    public Long save(MembershipDto dto){
        return membershipRepository.save(dto.toEntity()).getGrade();
    }
}
