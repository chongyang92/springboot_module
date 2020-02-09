package com.weboot.springboot.controller.rbac;

import com.weboot.springboot.controller.param.PermValidator;
import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.domain.Perm;
import com.weboot.springboot.domain.PermMenuKey;
import com.weboot.springboot.domain.PermPathKey;
import com.weboot.springboot.mapper.PermMenuMapper;
import com.weboot.springboot.mapper.PermPathMapper;
import com.weboot.springboot.service.PermService;
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
@RequestMapping("/perm")
public class PermController {
    private static final Logger logger = LoggerFactory.getLogger(PermController.class);

    @Resource
    private PermService permService;
    @Resource
    private PermMenuMapper permMenuMapper;
    @Resource
    private PermPathMapper permPathMapper;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result listPerm(@RequestBody PermValidator permValidator, BindingResult bindingResult) {

        Perm perm = permValidator.genPerm();
        List<Perm> permList = permService.getPermlist(perm);
        return ResultBuilder.genSuccessResult(permList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result createPerm(@Valid @RequestBody PermValidator permValidator, BindingResult bindingResult) {

        Perm perm = permValidator.genPerm();

        String permId = permService.insertPerm(perm);
        //绑定menu
        if(permValidator.getMenuIds() != null && !permValidator.getMenuIds().isEmpty()) {
            for (String menuId : permValidator.getMenuIds()) {
                PermMenuKey permMenuKey = new PermMenuKey();
                permMenuKey.setPermId(permId);
                permMenuKey.setMenuId(menuId);
                permMenuMapper.insert(permMenuKey);
            }
        }
        //绑定path
        if(permValidator.getPathIds() != null && !permValidator.getPathIds().isEmpty()) {
            for (String pathId : permValidator.getPathIds()) {
                PermPathKey permPathKey = new PermPathKey();
                permPathKey.setPermId(permId);
                permPathKey.setPathId(pathId);
                permPathMapper.insert(permPathKey);
            }
        }

        return ResultBuilder.genSuccessResult(perm);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result editPerm(@RequestBody PermValidator permValidator, BindingResult bindingResult) {

        Perm perm = permValidator.genPerm();
        permService.editPerm(perm);

        return ResultBuilder.genSuccessResult(perm);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deletePerm(@RequestBody PermValidator permValidator, BindingResult bindingResult) {

        Perm perm = permValidator.genPerm();
        permService.deletePerm(perm.getPermId());
        return ResultBuilder.genSuccessResult();
    }
}
