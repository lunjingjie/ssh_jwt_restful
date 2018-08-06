package com.ssh.jwt.controller;

import com.ssh.jwt.service.PageResourceService;
import com.ssh.jwt.vo.ResourceTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("web/pageResource")
@Api(tags = "页面资源信息")
public class PageResourceController {

    private final PageResourceService pageResourceService;

    @Autowired
    public PageResourceController(PageResourceService pageResourceService) {
        this.pageResourceService = pageResourceService;
    }

    @GetMapping("/getAllResource")
    @ApiOperation(httpMethod = "GET", value = "获取所有页面资源信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResourceTreeVo getAllResource() {
        return pageResourceService.loadAllResourceTree();
    }
}
