package com.weboot.springboot.controller;

import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@RequestMapping("/file")
public class InputStreamCrontroller {
    @RequestMapping("createFile")
    public Result createFile(){
        return ResultBuilder.genSuccessResult();

    }
}
