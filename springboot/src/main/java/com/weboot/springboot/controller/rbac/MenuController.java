package com.weboot.springboot.controller.rbac;

import com.weboot.springboot.controller.param.MenuValidator;
import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.domain.Menu;
import com.weboot.springboot.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private MenuService menuService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Result listMenu(@RequestBody MenuValidator menuValidator, BindingResult bindingResult){

        Menu menu = menuValidator.genMenu();
        List<Menu> menuList = menuService.getMenulist(menu);
        Menu menu1 = new Menu();
        menu1.setMenuId("123");
        menu1.setMenuName(null);
        menuList.add(menu1);
        return ResultBuilder.genSuccessResult(menuList);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Result createMenu(@Valid @RequestBody MenuValidator menuValidator, BindingResult bindingResult){

        Menu menu = menuValidator.genMenu();

        menuService.insertMenu(menu);

        return ResultBuilder.genSuccessResult(menu);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Result editMenu(@RequestBody MenuValidator menuValidator, BindingResult bindingResult){

        Menu menu = menuValidator.genMenu();
        menuService.editMenu(menu);

        return ResultBuilder.genSuccessResult(menu);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result deleteMenu(@RequestBody MenuValidator menuValidator, BindingResult bindingResult){

        Menu menu = menuValidator.genMenu();
        menuService.deleteMenu(menu.getMenuId());
        return ResultBuilder.genSuccessResult();
    }
}
