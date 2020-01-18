package com.weboot.springboot.controller.rbac;

import com.baidu.fsg.uid.UidGenerator;
import com.weboot.springboot.controller.param.OrgValidator;
import com.weboot.springboot.domain.Org;
import com.weboot.springboot.service.OrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Calendar;
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
    public List<Org> listOrg(@RequestBody OrgValidator orgValidator, BindingResult bindingResult){

        //System.out.println(uidGenerator.getUID());

        return orgService.getOrglist();
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createOrg(@Valid @RequestBody OrgValidator orgValidator, BindingResult bindingResult){

        Org org = orgValidator.genOrg();

        orgService.insertOrg(org);

        return org.toString();
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String editOrg(@RequestBody OrgValidator orgValidator, BindingResult bindingResult){

        Org org = orgValidator.genOrg();
        orgService.editOrg(org);

        return org.toString();
    }
}
