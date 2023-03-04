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
    @GetMapping("/somDaySalSel")
    public String SomDaySalSel(Model model) {
        model.addAttribute("salesVO", new SalesVO());
        return "thymeleafGym/somDaySalSelView";
    }

    @PostMapping("/resSomDaySalSel")
    public String ResSomDaySalSel(@ModelAttribute("salesVO") SalesVO salesVO,Model model){
        SalesDAO dao = new SalesDAO();
        List<SalesVO> list = dao.somDaySalSel(salesVO);
        model.addAttribute("salesList", list);
        return "thymeleafGym/somDaySalSelRes";
    }
    @GetMapping("/somMonthSalSel")
    public String SomMonthSalSel(Model model) {
        model.addAttribute("salesVO", new SalesVO());
        return "thymeleafGym/somMonthSalSelView";
    }

    @PostMapping("/resSomMonthSalSel")
    public String ResSomMonthSalSel(@ModelAttribute("salesVO") SalesVO salesVO,Model model){
        SalesDAO dao = new SalesDAO();
        List<SalesVO> list = dao.somMonthSalSel(salesVO);
        model.addAttribute("salesList", list);
        return "thymeleafGym/somMonthSalSelRes";
    }
    @GetMapping("/somYearSalSel")
    public String SomYearSalSel(Model model) {
        model.addAttribute("salesVO", new SalesVO());
        return "thymeleafGym/somYearSalSelView";
    }

    @PostMapping("/resSomYearSalSel")
    public String ResSomYearSalSel(@ModelAttribute("salesVO") SalesVO salesVO,Model model){
        SalesDAO dao = new SalesDAO();
        List<SalesVO> list = dao.somYearSalSel(salesVO);
        model.addAttribute("salesList", list);
        return "thymeleafGym/somYearSalSelRes";
    }
}
