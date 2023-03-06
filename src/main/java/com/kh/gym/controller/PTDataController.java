package com.kh.gym.controller;

import com.kh.gym.dao.PTDataDAO;
import com.kh.gym.vo.MemberInfoVO;
import com.kh.gym.vo.PTDataVO;
import com.kh.gym.vo.PTDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gym/ptdata")
public class PTDataController {
    @Autowired
    private PTDataDAO dao;
    @GetMapping("/pt_member")
    public String ptMemberList(Model model){
        List<MemberInfoVO> ptMemberList = dao.ptMemberList();
        System.out.println("PT 회원 조회");
        model.addAttribute("pt_member", ptMemberList);
        return "thymeleafGym/ptMemberSelectView";
    }

    @GetMapping("/data")
    public String getPTInfoList(Model model) {
        List<PTDataVO> PTData = dao.getPTInfoList();
        System.out.println("PT 기록 조회");
        model.addAttribute("pt_data", PTData);
        return "thymeleafGym/ptDataSelectView";
    }
    // 회원번호로 PT 기록 뷰
    @GetMapping("/{mem_Id}")
    public String getPTInfoList(@PathVariable("mem_Id") int mem_Id, Model model ){
        List<PTDataVO> PTData = dao.getPTInfoList(mem_Id);
        model.addAttribute("pt_data", PTData);
        return "thymeleafGym/ptDataSelectView";
    }
    @GetMapping("/search/form")
    public String showSearchForm() {
        return "thymeleafGym/searchPTData";
    }

    @GetMapping("/search/result")
    public String searchPTData(@RequestParam("mem_Id") int mem_Id, Model model) {
        List<PTDataVO> PTData = dao.getPTInfoList(mem_Id);
        model.addAttribute("pt_data", PTData);
        return "thymeleafGym/ptDataSelectView";
    }

    // PT 기록 삽입
    @GetMapping("/insert")
    public String insertPTData(Model model) {
        model.addAttribute("pt_data", new PTDatabase());
        System.out.println("PT 기록 요청");
        return "thymeleafGym/ptDataInsert";
    }
    @PostMapping("/insert")
    public String savePTData(@ModelAttribute("pt_data") PTDatabase ptDatabase) {
        dao.PTDataInsert(ptDatabase);
        System.out.println("PT 기록 성공");
        return "redirect:/gym/ptdata/" + ptDatabase.getMem_ID();
    }
//
//    // PT 기록 삭제 어라 어떻게 구현하지? 행번호를 가져와서 삭제하는 구문이었는데 이거 참..
//    @GetMapping("/delete/form")
//    public String ptDataDeleteForm(){
//        return "thymeleafGym/searchDeletePTData";
//    }
//    @GetMapping("/delete/{mem_Id}")
//
//
//    @GetMapping("/delete/result")
//    public String deletePTData(@RequestParam("rowNo") int rowNo, Model model) {
//
//    }



}
