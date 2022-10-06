package study.shoppingmall.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.shoppingmall.domain.Member;
import study.shoppingmall.dto.MemberDto;
import study.shoppingmall.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;


    public Member createMember() {
        MemberDto memberDto = MemberDto.builder()
                .email("test@email.com")
                .name("테스트")
                .address("부산")
                .password("1111")
                .build();
        return Member.createMember(memberDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        Member member = createMember();
        Member saveMember = memberService.saveMember(member);

        assertEquals(member.getEmail(),saveMember.getEmail());
    }
}

