package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.PlatformUserMapper;
import com.zhongke.pojo.PlatformUser;
import com.zhongke.service.PlatformUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public PageInfo<PlatformUser> platUsers(PlatformUser platformUser, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(platformUser);
        return new PageInfo<>(platformUserMapper.selectByExample(example));
    }

    @Override
    public void update(PlatformUser platformUser, int id) {
        platformUserMapper.updateByPrimaryKey(platformUser);
    }

    @Override
    public PlatformUser findById(int id) {
        return platformUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public PlatformUser findByPlatformUserName(String platformUserName) {
        return platformUserMapper.findByPlatformUserName(platformUserName);
    }

    @Override
    public void add(PlatformUser platformUser) {
        platformUserMapper.insertSelective(platformUser);
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
                criteria.andEqualTo("password",platformUser.getPassword());
            }
            // 用户昵称
            if (!StringUtils.isEmpty(platformUser.getNickname())){
                criteria.andEqualTo("nickname",platformUser.getNickname());
            }
            // 用户所属商户id
            if (!StringUtils.isEmpty(platformUser.getMerchantId())){
                criteria.andEqualTo("merchantId",platformUser.getMerchantId());
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
