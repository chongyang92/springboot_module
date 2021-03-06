package com.weboot.springboot.controller.rbac;

import com.baidu.fsg.uid.UidGenerator;
import com.weboot.springboot.controller.param.OrgValidator;
import com.weboot.springboot.model.Org;
import com.weboot.springboot.service.OrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Validated
@RequestMapping("/org")
public class OrgController {
    private static final Logger logger = LoggerFactory.getLogger(OrgController.class);

    @Resource
    private OrgService orgService;
    @Resource
    private UidGenerator uidGenerator;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public List<Org> login(@RequestBody OrgValidator orgValidator, BindingResult bindingResult){

        System.out.println(uidGenerator.getUID());

        return orgService.getOrglist();
    }
}
