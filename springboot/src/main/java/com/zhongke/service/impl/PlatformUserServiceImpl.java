package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.MenuMapper;
import com.zhongke.mapper.PlatformUserMapper;
import com.zhongke.pojo.Menu;
import com.zhongke.pojo.PlatformUser;
import com.zhongke.service.PlatformUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName PlatformUserServiceImpl
 * @Description 平台用户
 * @Author liuli
 * @Date 2020/4/2 17:04
 * @Version 1.0
 **/
@Service
public class PlatformUserServiceImpl implements PlatformUserService {

    @Autowired(required = false)
    private PlatformUserMapper platformUserMapper;
    @Autowired(required = false)
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired(required = false)
    private MenuMapper menuMapper;

    @Override
    public PageInfo<PlatformUser> platUsers(String name, String tel , int page, int size) {
        PageHelper.startPage(page,size);
        return new PageInfo<>(platformUserMapper.selectAllByExample(name,tel));
    }

    @Override
    public void update(PlatformUser platformUser, int id) {
        platformUserMapper.updateByPrimaryKey(platformUser);
    }

    @Override
    public PlatformUser findById(int id) {
        return platformUserMapper.selectById(id);
    }

    @Override
    public PlatformUser findByPlatformUserName(String platformUserName) {
        return platformUserMapper.findByPlatformUserName(platformUserName);
    }

    @Override
    public void add(PlatformUser platformUser) {
        platformUser.setPassword(bCryptPasswordEncoder.encode(platformUser.getPassword()));
        platformUserMapper.insertSelective(platformUser);
    }

    @Override
    public List<Menu> getMenu() {
        //查询所有菜单
        List<Menu> allMenu = menuMapper.selectAll();
        //根节点
        List<Menu> rootMenu = new ArrayList<Menu>();
        for (Menu nav : allMenu) {
            if(nav.getParentMenuId().equals("0")){//父节点是0的，为根节点。
                rootMenu.add(nav);
            }
        }
        /* 根据Menu类的order排序 */
        Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (Menu nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<Menu> childList = getChild(nav.getId(), allMenu);
            nav.setChildren(childList);//给根节点设置子节点
        }
        return rootMenu;
    }
    /**
     * 获取子节点
     * @param id 父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    private List<Menu> getChild(Integer id,List<Menu> allMenu){
        //子菜单
        List<Menu> childList = new ArrayList<Menu>();
        for (Menu nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if(nav.getParentMenuId().equals(id)){
                childList.add(nav);
            }
        }
        //递归
        for (Menu nav : childList) {
            nav.setChildren(getChild(nav.getId(), allMenu));
        }
        Collections.sort(childList,order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<Menu>();
        }
        return childList;
    }

    /**
     * @Description 菜单排序
     * @author liuli
     * @date 2020/5/11 17:20
     * @param
     * @return java.util.Comparator<com.zhongke.pojo.Menu>
     **/
    public Comparator<Menu> order(){
        Comparator<Menu> comparator = new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                if(o1.getSort() != o2.getSort()){
                    return o1.getSort() - o2.getSort();
                }
                return 0;
            }
        };
        return comparator;
    }

    /**
     * @Description 构建搜索条件
     * @author liuli
     * @date 2020/4/2 17:27
     * @param platformUser
     * @return tk.mybatis.mapper.entity.Example
     **/
    public Example createExample(PlatformUser platformUser){
        Example example = new Example(PlatformUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (platformUser!=null){
            // 用户id
            if (!StringUtils.isEmpty(platformUser.getId())){
                criteria.andEqualTo("id",platformUser.getId());
            }
            // 用户姓名
            if (!StringUtils.isEmpty(platformUser.getName())){
                criteria.andEqualTo("name",platformUser.getName());
            }
            // 用户密码
            if (!StringUtils.isEmpty(platformUser.getPassword())){
                criteria.andEqualTo("password",bCryptPasswordEncoder.encode(platformUser.getPassword()));
            }
            // 用户昵称
            if (!StringUtils.isEmpty(platformUser.getNickname())){
                criteria.andEqualTo("nickname",platformUser.getNickname());
            }
            // 用户所属门店名称
            if (!StringUtils.isEmpty(platformUser.getStoreName())){
                criteria.andEqualTo("storeName",platformUser.getStoreName());
            }
            // 性别
            if (!StringUtils.isEmpty(platformUser.getSex())){
                criteria.andEqualTo("sex",platformUser.getSex());
            }
            // 状态（y 启用 n 禁用）
            if (!StringUtils.isEmpty(platformUser.getStatus())){
                criteria.andEqualTo("status",platformUser.getStatus());
            }
            // 手机号
            if (!StringUtils.isEmpty(platformUser.getTel())){
                criteria.andEqualTo("tel",platformUser.getTel());
            }
            // 图片url
            if (!StringUtils.isEmpty(platformUser.getImage())){
                criteria.andEqualTo("image",platformUser.getImage());
            }
            // 更新时间
            if (!StringUtils.isEmpty(platformUser.getCreateTime())){
                criteria.andEqualTo("createTime",platformUser.getCreateTime());
            }
        }
        return example;
    }
}
