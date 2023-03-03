package com.kh.gym.controller;

import com.kh.gym.dao.MemberInfoDAO;
import com.kh.gym.vo.MemberInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/select")

public class MemberInfoSelectController {

    @GetMapping("/memberinfo")
    public String memberInfoSelect(Model model){
        MemberInfoDAO dao = new MemberInfoDAO();
        List<MemberInfoVO> memberinfo = dao.M_InfoSelect();
        System.out.println("회원정보 요청");
        model.addAttribute("memberinfo", memberinfo);
        return "thymeleafGym/memberInfoSelectView";
    }

}
