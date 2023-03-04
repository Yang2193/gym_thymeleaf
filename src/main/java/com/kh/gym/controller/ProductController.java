package com.kh.gym.controller;

import com.kh.gym.dao.MemberInfoDAO;
import com.kh.gym.dao.ProductDAO;
import com.kh.gym.vo.MemberInfoVO;
import com.kh.gym.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gym/product")
public class ProductController {

    @Autowired
    private ProductDAO dao;
    @GetMapping("/select")
    public String selectProduct(Model model){
        List<ProductVO> product = dao.productSel();
        System.out.println("상품정보 요청");
        model.addAttribute("product", product);
        return "thymeleafGym/productSelectView";
    }

    @GetMapping("/insert")
    public String insertProduct(Model model){
        model.addAttribute("product", new ProductVO());
        System.out.println("상품등록 요청");
        return "thymeleafGym/productInsert";
    }

    @PostMapping("/insert")
    public String saveProduct(@ModelAttribute("product") ProductVO product){
        dao.productInsert(product);
        System.out.println("상품등록 성공");
        return "thymeleafGym/productSelectView";
    }

    @GetMapping("/delete/form")
    public String deleteProduct(){
        return "thymeleafGym/deleteProduct";
    }

    @GetMapping("/delete/result")
    public String deleteProduct2(@RequestParam("pName") String pName, Model model ){
        dao.productDelete(pName);
        List<ProductVO> product = dao.productSel();
        System.out.println("상품정보 요청");
        model.addAttribute("product", product);
        return "thymeleafGym/productSelectView";
    }

}
