package com.weboot.springboot.controller.rbac;

import com.weboot.springboot.controller.param.RoleValidator;
import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.domain.Role;
import com.weboot.springboot.domain.RolePermKey;
import com.weboot.springboot.mapper.RolePermMapper;
import com.weboot.springboot.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;
    @Resource
    private RolePermMapper rolePermMapper;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result listRole(@RequestBody RoleValidator roleValidator, BindingResult bindingResult) {

        Role role = roleValidator.genRole();
        List<Role> roleList = roleService.getRolelist(role);
        return ResultBuilder.genSuccessResult(roleList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result createRole(@Valid @RequestBody RoleValidator roleValidator, BindingResult bindingResult) {

        Role role = roleValidator.genRole();

        String roleId = roleService.insertRole(role);

        //绑定perm
        if(roleValidator.getPermIds() != null && !roleValidator.getPermIds().isEmpty()) {
            for (String permId : roleValidator.getPermIds()) {
                RolePermKey rolePermKey = new RolePermKey();
                rolePermKey.setRoleId(roleId);
                rolePermKey.setPermId(permId);
                rolePermMapper.insert(rolePermKey);
            }
        }

        return ResultBuilder.genSuccessResult(role);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result editRole(@RequestBody RoleValidator roleValidator, BindingResult bindingResult) {

        Role role = roleValidator.genRole();
        roleService.editRole(role);

        return ResultBuilder.genSuccessResult(role);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteRole(@RequestBody RoleValidator roleValidator, BindingResult bindingResult) {

        Role role = roleValidator.genRole();
        roleService.deleteRole(role.getRoleId());
        return ResultBuilder.genSuccessResult();
    }
}
