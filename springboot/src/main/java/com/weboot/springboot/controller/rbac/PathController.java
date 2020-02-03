package com.weboot.springboot.controller.rbac;

import com.weboot.springboot.controller.param.PathValidator;
import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.domain.Path;
import com.weboot.springboot.service.PathService;
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
@RequestMapping("/path")
public class PathController {
    private static final Logger logger = LoggerFactory.getLogger(PathController.class);

    @Resource
    private PathService pathService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result listPath(@RequestBody PathValidator pathValidator, BindingResult bindingResult) {

        Path path = pathValidator.genPath();
        List<Path> pathList = pathService.getPathlist(path);
        return ResultBuilder.genSuccessResult(pathList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result createPath(@Valid @RequestBody PathValidator pathValidator, BindingResult bindingResult) {

        Path path = pathValidator.genPath();

        pathService.insertPath(path);

        return ResultBuilder.genSuccessResult(path);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result editPath(@RequestBody PathValidator pathValidator, BindingResult bindingResult) {

        Path path = pathValidator.genPath();
        pathService.editPath(path);

        return ResultBuilder.genSuccessResult(path);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deletePath(@RequestBody PathValidator pathValidator, BindingResult bindingResult) {

        Path path = pathValidator.genPath();
        pathService.deletePath(path.getPathId());
        return ResultBuilder.genSuccessResult();
    }
}

