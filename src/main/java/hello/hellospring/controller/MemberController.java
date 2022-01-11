package hello.hellospring.controller;

import hello.hellospring.controller.repository.MemberRepository;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //이 어노테이션을 보고 해당 객체를 생성해 스프링 컨테이너에 집어넣음.
public class MemberController {
    //private final MemberService memberService = new MemberService();    //문제? 다른 객체들이 MemberService를 가져다 쓸 수 있는 문제. 하나를 생성해서 공용으로 쓰는 것이 바람직.

    private final MemberService memberService;

//    @Autowired
//    public void setMemberService(MemberService memberService) { //setter 주입 : DI
//        this.memberService = memberService;
//        //memberService.join(memberService);    //아무 개발자나 호출할 수 있게 됨.
//    }

//    @Autowired private final MemberService memberService;   //필드 주입 : DI

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService; //생성자를 통해서 멤버서비스가 주입 : DI
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
//    public static void main(String[] args){
//        MemberService memberService = new MemberService(member);
//    }

    @PostMapping("/members/new")
    public String Create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        //System.out.println(member.getName());
        return "redirect:/";    //home 화면으로 보내는 친구.
    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
