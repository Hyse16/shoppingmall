package study.shoppingmall.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.shoppingmall.domain.Member;
import study.shoppingmall.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kim");
        Long saveId = memberService.join(member);
        assertEquals(member,memberService.findOne(saveId));
    }

    @Test
    public void 중복회원테스트() throws Exception {

        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e){
            return;
        }
    }
}