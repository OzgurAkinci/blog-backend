package com.app.blog.repository;


import com.app.blog.domain.SUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface SUserRoleDao extends JpaRepository<SUserRole, Integer>, UserRoleDaoCustom{


}

