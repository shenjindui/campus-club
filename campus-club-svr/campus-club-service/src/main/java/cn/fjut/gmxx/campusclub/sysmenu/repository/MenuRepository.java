package cn.fjut.gmxx.campusclub.sysmenu.repository;

import cn.fjut.gmxx.campusclub.sysmenu.entity.SysMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<SysMenuEntity,String>,
        JpaSpecificationExecutor<SysMenuEntity> {

    /**
     * 根据menuName获取菜单详情
     * @param menuName
     * @return
     */
    SysMenuEntity  findByMenuName(String menuName);

    /**
     * 根据uuid获取菜单详情
     * @param uuid
     * @return
     */
    SysMenuEntity  findByUuid(String uuid);

    /**
     * 获取最大的菜单编号
     * @return
     */
    @Query(value = "SELECT max(menu_code) from sys_menu",nativeQuery = true)
    String  findSysMenuMaxMenuCode();
}
