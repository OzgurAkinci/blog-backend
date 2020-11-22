package com.app.blog.repository;


import com.app.blog.domain.SPermission;
import com.app.blog.domain.SRole;

import java.util.List;

public interface UserRoleDaoCustom {
    public List<SRole> findByUser(Integer userId);

    public List<SRole> findByPermission(Integer permissionId);

    public List<SPermission> findPermissionByRole(Integer roleId);
}
