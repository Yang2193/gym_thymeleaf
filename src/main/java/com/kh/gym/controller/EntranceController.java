package com.kh.gym.controller;

import com.kh.gym.dao.EntranceDAO;
import com.kh.gym.vo.EntranceVO;
import com.kh.gym.vo.MemberEntranceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/gym/entrance")
public class EntranceController {

    @Autowired
    private EntranceDAO dao;

    @GetMapping("/search/id")
    public String searchEntId(){
        return "thymeleafGym/searchEntranceId";
    }

    @GetMapping("/search/idRst")
    public String searchEntId2(@RequestParam("id") int id, Model model){
        List<EntranceVO> entranceMemberList = dao.showMemberAttendanceList(id);
        model.addAttribute("entranceMemberList", entranceMemberList);
        return "thymeleafGym/entranceSelectViewId";
    }

    @GetMapping("/search/date")
    public String searchEntDate(){
        return "thymeleafGym/searchEntranceDate";
    }

    @GetMapping("/search/dateRst")
    public String searchEntDate2(@RequestParam("date") String date, Model model){
        List<EntranceVO> entranceMemberList = dao.showDayList(date);
        model.addAttribute("entranceMemberList", entranceMemberList);
        return "thymeleafGym/entranceSelectViewTime";
    }

    @GetMapping("/todaylist")
    public String showTodayList(Model model){
        List<EntranceVO> entranceMemberList = dao.showTodayList();
        model.addAttribute("entranceMemberList", entranceMemberList);
        System.out.println("오늘 입장한 회원 출력");
        return "thymeleafGym/entranceSelectViewTime";
    }

    @GetMapping("/insert/form")
    public String insertEntranceData(){
        return "thymeleafGym/entranceInsert";
    }

    @GetMapping("/insert/result")
    public String insertEntranceData2(@RequestParam("mem_Id") int mem_Id, Model model){
        List<MemberEntranceVO> entranceMemberList = dao.enterMember(mem_Id);
        model.addAttribute("entranceMemberList", entranceMemberList);
        return "thymeleafGym/entranceInsertView";
    }
}
