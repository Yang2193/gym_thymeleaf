package com.kh.gym.controller;

import com.kh.gym.dao.MemberInfoDAO;
import com.kh.gym.vo.MemberInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gym/memberinfo")

public class MemberInfoController {

    @Autowired
    private MemberInfoDAO dao;

    // 전체 회원 정보를 조회하는 메서드
    @GetMapping("/selectAll")
    public String memberInfoSelect(Model model){
        List<MemberInfoVO> memberinfo = dao.M_InfoSelect();
        System.out.println("회원정보 요청");
        model.addAttribute("memberinfo", memberinfo);
        return "thymeleafGym/memberInfoSelectView";
    }

    @GetMapping("/{mem_Id}")
    public String specificMemberInfo(@PathVariable("mem_Id") int mem_Id, Model model ){
        List<MemberInfoVO> memberinfo = dao.M_SelectSomeone(mem_Id);
        model.addAttribute("memberinfo", memberinfo);
        return "thymeleafGym/memberInfoSelectView";
    }


    // 검색 폼 페이지를 출력하는 메서드
    @GetMapping("/search/form")
    public String showSearchForm() {
        return "thymeleafGym/searchMemberInfo";
    }

    // 회원번호(mem_Id)로 회원 정보를 조회하는 메서드
    @GetMapping("/search/result")
    public String searchMemberInfo(@RequestParam("mem_Id") int mem_Id, Model model) {
        List<MemberInfoVO> memberinfo = dao.M_SelectSomeone(mem_Id);
        model.addAttribute("memberinfo", memberinfo);
        return "thymeleafGym/memberInfoSelectView";
    }

    //회원등록하는 메서드
    @GetMapping("/insert")
    public String insertMemberInfo(Model model) {
        model.addAttribute("memberinfo", new MemberInfoVO());
        System.out.println("회원등록 요청");
        return "thymeleafGym/memberInfoInsert";
    }
    @PostMapping("/insert")
    public String saveMemberInfo(@ModelAttribute("memberinfo") MemberInfoVO memberinfo) {
        dao.M_InfoInsert(memberinfo);
        System.out.println("회원등록 성공");
        return "redirect:/gym/memberinfo/" + memberinfo.getMem_Id();
    }

    //만기회원조회

    @GetMapping("/expired")
    public String expiredMember(Model model){
        List<MemberInfoVO> memberinfo = dao.expiredDateList();
        model.addAttribute("memberinfo", memberinfo);
        return "thymeleafGym/memberInfoSelectView";
    }

    // 삭제 폼 페이지를 출력하는 메서드
    @GetMapping("/delete/form")
    public String deleteMemberInfo() {
        return "thymeleafGym/deleteMemberInfo";
    }

    // 삭제 실행
    @GetMapping("/delete/result")
    public String deleteMemberInfo2(@RequestParam("mem_Id") int mem_Id, Model model) {
        dao.m_InfoDelete(mem_Id);
        List<MemberInfoVO> memberinfo = dao.M_InfoSelect();
        model.addAttribute("memberinfo", memberinfo);
        return "thymeleafGym/memberInfoSelectView";
    }

    // 회원정보 update 폼

    @GetMapping("/updatenum")
    public String updateMemberInput(){
        return "thymeleafGym/updateMemberInfoInput";
    }
    @GetMapping("/update")
    public String updateMemberInfo(@RequestParam("mem_Id") int mem_Id, Model model){
        MemberInfoVO member = dao.M_SelectSomeone2(mem_Id);
        model.addAttribute("member", member);
        return "thymeleafGym/updateMemberInfo";
    }

    // 회원정보 수정 처리
    @PostMapping("/update_member")
    public String updateMemberInfo2(@ModelAttribute("member") MemberInfoVO member) {
        try {
            dao.m_infoModify(member);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/gym/memberinfo/" + member.getMem_Id();

    }


}
