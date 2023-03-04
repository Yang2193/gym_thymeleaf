package com.kh.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gym")
public class MenuController {
    @GetMapping("/menu/memberinfo")
    public String memberinfoMenu(){
        return "thymeleafGym/memberInfoMenu";
    }

    @GetMapping("/menu/product")
    public String productMenu(){ return "thymeleafGym/productMenu"; }

    @GetMapping("/menu")
    public String gymMenu(){return "thymeleafGym/gymMenu";}

    @GetMapping("/menu/main")
    public String mainMenu(){return "thymeleafGym/mainMenu";}

    @GetMapping("/menu/entrance")
    public String entranceMenu(){return "thymeleafGym/entranceMenu";}

    @GetMapping("/menu/locker")
    public String lockerMenu(){return "thymeleafGym/lockerMenu";}

}
