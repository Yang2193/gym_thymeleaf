package com.kh.gym.controller;

import com.kh.gym.dao.SalesDAO;
import com.kh.gym.vo.SalesVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/select")
public class SalesSelectController {
    @GetMapping("/dailySalSel")
    public String dailySalSel(Model model) {
        SalesDAO dao = new SalesDAO();
        List<SalesVO> dailySalSel = dao.dailySalSel();
        model.addAttribute("dailySalSel", dailySalSel);
        return "thymeleafGym/dailySalSelView";
    }
    @GetMapping("/monthlySalSel")
    public String monthlySalSel(Model model) {
        SalesDAO dao = new SalesDAO();
        List<SalesVO> monthlySalSel = dao.monthlySalSel();
        model.addAttribute("monthlySalSel", monthlySalSel);
        return "thymeleafGym/monthlySalSelView";
    }
    @GetMapping("/annualSalSel")
    public String annualSalSel(Model model) {
        SalesDAO dao = new SalesDAO();
        List<SalesVO> annualSalSel = dao.annualSalSel();
        model.addAttribute("annualSalSel", annualSalSel);
        return "thymeleafGym/annualSalSelView";
    }
    @GetMapping("/oneDaySalSel")
    public String OneDaySalSel(Model model) {
        model.addAttribute("salesVO", new SalesVO());
        return "thymeleafGym/oneDaySalSelView";
    }

    @PostMapping("/resOneDaySalSel")
    public String ResOneDaySalSel(@ModelAttribute("salesVO") SalesVO salesVO,Model model){
        SalesDAO dao = new SalesDAO();
        List<SalesVO> list = dao.oneDaySalSel(salesVO);
        model.addAttribute("salesList", list);
        return "thymeleafGym/oneDaySalSelRes";
    }
    @GetMapping("/oneMonthSalSel")
    public String OneMonthSalSel(Model model) {
        model.addAttribute("salesVO", new SalesVO());
        return "thymeleafGym/oneMonthSalSelView";
    }

    @PostMapping("/resOneMonthSalSel")
    public String ResOneMonthSalSel(@ModelAttribute("salesVO") SalesVO salesVO,Model model){
        SalesDAO dao = new SalesDAO();
        List<SalesVO> list = dao.oneMonthSalSel(salesVO);
        model.addAttribute("salesList", list);
        return "thymeleafGym/oneMonthSalSelRes";
    }
    @GetMapping("/oneYearSalSel")
    public String OneYearSalSel(Model model) {
        model.addAttribute("salesVO", new SalesVO());
        return "thymeleafGym/oneYearSalSelView";
    }

    @PostMapping("/resOneYearSalSel")
    public String ResOneYearSalSel(@ModelAttribute("salesVO") SalesVO salesVO,Model model){
        SalesDAO dao = new SalesDAO();
        List<SalesVO> list = dao.oneYearSalSel(salesVO);
        model.addAttribute("salesList", list);
        return "thymeleafGym/oneYearSalSelRes";
    }
}
