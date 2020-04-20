package cn.fjut.gmxx.campusclub.sysmenu.repository;

import cn.fjut.gmxx.campusclub.sysmenu.entity.SysMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface MenuRepository extends JpaRepository<SysMenuEntity,String>, JpaSpecificationExecutor<SysMenuEntity> {

    SysMenuEntity  findByMenuName(String menuName);

    SysMenuEntity  findByUuid(String uuid);

    @Query(value = "SELECT max(menu_code) from sys_menu",nativeQuery = true)
    String  findSysMenuMaxMenuCode();


}
