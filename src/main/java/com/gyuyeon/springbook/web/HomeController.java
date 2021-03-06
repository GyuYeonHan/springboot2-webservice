package com.gyuyeon.springbook.web;

import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@Login User loginUser, Model model) {
        //세션에 회원 데이터가 없으면
        if (loginUser == null) {
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);
        return "loginHome";
    }

    @GetMapping("/cat")
    public String cat() {
        return "playground/kitten";
    }
}