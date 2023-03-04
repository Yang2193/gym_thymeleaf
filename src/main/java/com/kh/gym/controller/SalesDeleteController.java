package com.kh.gym.controller;
import com.kh.gym.dao.SalesDAO;
import com.kh.gym.vo.SalesVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class SalesDeleteController {
    @GetMapping("/getSales")
    public String deleteSalesFrom(Model model) {
        model.addAttribute("sales", new SalesVO());
        return "thymeleafGym/salesDeleteView";
    }

    @PostMapping("/postSales")
    public String deleteSales(@ModelAttribute("sales") SalesVO salesVO) {
        SalesDAO dao = new SalesDAO();
        dao.salesDelete(salesVO);
        return "thymeleafGym/salesDeleteRes";
    }
}
