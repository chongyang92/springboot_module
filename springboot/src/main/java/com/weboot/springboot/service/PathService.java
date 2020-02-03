package com.weboot.springboot.service;

import com.weboot.springboot.domain.Path;

import java.util.List;

public interface PathService {
    List<Path> getPathlist(Path path);
    String insertPath(Path path);
    String editPath(Path path);
    String deletePath(String pathId);
}
