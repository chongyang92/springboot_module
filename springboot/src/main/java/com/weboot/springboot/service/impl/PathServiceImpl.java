package com.weboot.springboot.service.impl;

import com.baidu.fsg.uid.UidGenerator;
import com.weboot.springboot.domain.Path;
import com.weboot.springboot.domain.PathExample;
import com.weboot.springboot.exception.ServiceException;
import com.weboot.springboot.mapper.PathMapper;
import com.weboot.springboot.service.PathService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PathServiceImpl implements PathService {

    private static final Logger logger = LoggerFactory.getLogger(PathServiceImpl.class);

    @Resource
    private PathMapper pathMapper;
    @Resource
    private UidGenerator uidGenerator;

    @Override
    public List<Path> getPathlist(Path path) {
        PathExample pathExample = new PathExample();
        PathExample.Criteria cc = pathExample.createCriteria();
        if (StringUtils.isNotBlank(path.getPathId())) {
            cc.andPathIdEqualTo(path.getPathId());
        }
        if (StringUtils.isNotBlank(path.getHttpPath())) {
            cc.andHttpPathLike("%" + path.getHttpPath() + "%");
        }
        if (StringUtils.isNotBlank(path.getHttpMethodType())) {
            cc.andHttpMethodTypeLike("%" + path.getHttpMethodType() + "%");
        }

        return pathMapper.selectByExample(pathExample);
    }

    @Override
    public String insertPath(Path path) {
        String pathId = String.valueOf(uidGenerator.getUID());
        path.setPathId(pathId);
        pathMapper.insert(path);
        return pathId;
    }

    @Override
    public String editPath(Path path) {
        pathMapper.updateByPrimaryKeySelective(path);
        return path.getHttpPath();
    }

    @Override
    public String deletePath(String pathId) {
        if (1 != pathMapper.deleteByPrimaryKey(pathId)) {
            throw new ServiceException("删除路径失败");
        }
        return pathId;
    }
}
