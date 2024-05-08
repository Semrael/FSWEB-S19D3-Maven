package com.workintech.s19d2.service;

import com.workintech.s19d2.dao.MemberRepository;
import com.workintech.s19d2.dto.RegistrationMember;
import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member registerNewMember(RegistrationMember registrationMember) throws IllegalArgumentException {
        if (registrationMember.email() == null || registrationMember.password() == null) {
            throw new IllegalArgumentException("email ve şifre boş olamaz.");
        }

        Optional<Member> existingMember = memberRepository.findByEmail(registrationMember.email());
        if (existingMember.isPresent()) {
            throw new IllegalArgumentException("Bu email ile kayıtlı bir hesap  var.");
        }
        Member newMember = new Member();
        newMember.setEmail(registrationMember.email());
        newMember.setPassword(passwordEncoder.encode(registrationMember.password()));

        Role defaultRole = new Role();
        defaultRole.setAuthority(registrationMember.id() == 1 ? "ADMIN" : "USER");
        newMember.setRoles((List<Role>) Collections.singleton(defaultRole));

        return memberRepository.save(newMember);
    }
}