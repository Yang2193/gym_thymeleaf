package com.kh.gym.controller;

import com.kh.gym.dao.LockerDAO;
import com.kh.gym.vo.LockerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/gym/locker")
public class LockerController {

    @Autowired
    private LockerDAO dao;

    @GetMapping("/entire")
    public String lockerEntire(Model model){
        List<LockerVO> lockerlist = dao.entireLockerList();
        model.addAttribute("lockerlist", lockerlist);
        System.out.println("라커정보 요청");
        return "thymeleafGym/lockerEntireView";
    }

    @GetMapping("/occupied")
    public String lockerOccupied(Model model){
        List<LockerVO> lockerlist = dao.occupiedLockerList();
        model.addAttribute("lockerlist", lockerlist);
        System.out.println("사용 중인 라커정보 요청");
        return "thymeleafGym/lockerOccupiedView";
    }

    @GetMapping("/updateInput")
    public String lockerUpdate(){
        return "thymeleafGym/lockerUpdate";
    }


    @GetMapping("/update_locker")
    public String lockerUpdate2(@RequestParam("lockNum") String lockNum,
                                @RequestParam("term") int term,
                                @RequestParam("id") int id, Model model){
        dao.updateLocker(lockNum, id, term);
        List<LockerVO> lockerlist = dao.occupiedLockerList();
        model.addAttribute("lockerlist", lockerlist);
        System.out.println("등록/수정 후 사용 중인 라커정보 요청");
        return "thymeleafGym/lockerOccupiedView";
    }

    @GetMapping("/init")
    public String lockerInit(){
        return "thymeleafGym/lockerInit";
    }

    @GetMapping("/initLocker")
    public String lockerInit2(@RequestParam("lockNum") String lockNum,
                              Model model){
        dao.initLocker(lockNum);
        dao.initMILocker(lockNum);
        List<LockerVO> lockerlist = dao.occupiedLockerList();
        model.addAttribute("lockerlist", lockerlist);
        System.out.println("등록/수정 후 사용 중인 라커정보 요청");
        return "thymeleafGym/lockerOccupiedView";
    }

}
