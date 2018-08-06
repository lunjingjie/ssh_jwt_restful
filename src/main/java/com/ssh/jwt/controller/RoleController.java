package com.ssh.jwt.controller;

import com.ssh.jwt.model.Role;
import com.ssh.jwt.service.PageResourceService;
import com.ssh.jwt.service.RoleService;
import com.ssh.jwt.vo.ResourceTreeVo;
import com.ssh.jwt.vo.RoleVo;
import com.ssh.jwt.vo.SaveRoleResourceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("web/role")
@Api(tags = "权限角色")
public class RoleController {

    private final PageResourceService pageResourceService;
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService, PageResourceService pageResourceService) {
        this.roleService = roleService;
        this.pageResourceService = pageResourceService;
    }

    @GetMapping("/list")
    @ApiOperation(httpMethod = "GET", value = "查看所有权限角色", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoleVo> getAllRoleList() {
        List<RoleVo> result = new ArrayList<>();
        List<Role> roleList = roleService.doQueryAll();
        for (Role role : roleList) {
            RoleVo roleVo = new RoleVo();
            roleVo.setRoleId(role.getRoleId());
            roleVo.setRoleName(role.getRoleName());
            result.add(roleVo);
        }
        return result;
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "创建单个权限角色", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addRole(RoleVo roleVo) {
        Role role = new Role();
        role.setRoleName(roleVo.getRoleName());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        role.setInsertTime(now);
        role.setIsDeleted("N");
        roleService.persist(role);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(httpMethod = "DELETE", value = "删除单个权限角色", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRole(@ApiParam("权限角色ID") @PathVariable(value = "id") int roleId) {
        roleService.deleteAudit("roleId", roleId);
    }

    @PutMapping
    @ApiOperation(httpMethod = "PUT", value = "更新权限角色信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateRole(RoleVo roleVo) {
        int roleId = roleVo.getRoleId();
        String roleName = roleVo.getRoleName();
        Role role = roleService.get(roleId);
        if(roleName != ""){
            role.setRoleName(roleName);
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        role.setUpdateTime(now);
        roleService.update(role);
    }

    @GetMapping("/{roleId}/pageResource")
    @ApiOperation(httpMethod = "GET", value = "根据权限角色ID获取对应菜单资源", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResourceTreeVo getResourceById(@ApiParam("权限角色ID") @PathVariable(value = "roleId") int roleId) {
        return pageResourceService.getResourceTreeByRoleId(roleId);
    }

    @PutMapping("/saveRoleResource")
    @ApiOperation(httpMethod = "PUT", value = "更新权限角色所拥有的资源", produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveRoleResource(SaveRoleResourceVo svo) {
        int roleId = svo.getRoleId();
        Role role = roleService.get(roleId);
        roleService.deleteRoleResource(role);
        roleService.saveRoleResource(role, svo);
    }
}
