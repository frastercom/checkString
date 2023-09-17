package com.example.checkstring.controllers;

import com.example.checkstring.utils.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CheckController {

    @PostMapping("/checkBrackets")
    public Map<String, Boolean> checkString(@RequestBody Map<String, String> obj) {
        return Collections.singletonMap("isCorrect", StringUtils.check(obj.get("text"), obj.get("symbols")));
    }
}
