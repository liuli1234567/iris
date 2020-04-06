package com.zhongke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongke.mapper.JamMapper;
import com.zhongke.mapper.MemberJamMapper;
import com.zhongke.pojo.Jam;
import com.zhongke.service.JamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @ClassName JamServiceImpl
 * @Description 卡卷
 * @Author liuli
 * @Date 2020/4/3 18:40
 * @Version 1.0
 **/
@Service
public class JamServiceImpl implements JamService {

    @Autowired(required = false)
    private JamMapper jamMapper;
    @Autowired(required = false)
    private MemberJamMapper memberJamMapper;

    @Override
    public PageInfo<Jam> jams(Jam jam, int page, int size) {
        Example example = new Example(Jam.class);
        Example.Criteria criteria = example.createCriteria();
        if (jam != null) {
            if (!StringUtils.isEmpty(jam.getName())) {
                criteria.andEqualTo(jam.getName());
            }
        }
        List<Jam> jams = jamMapper.selectByExample(example);
        Date date = new Date();
        if (jams != null && jams.size()>0) {
            for (Jam jam1 : jams) {
                int count = memberJamMapper.count(jam1.getId());
                jam1.setReceiveNum(count);
                if (date.getTime()-jam1.getClaiStartTime().getTime()>0){
                    if (date.getTime()-jam1.getClaiEndTime().getTime()<0){
                        jam1.setStatus(1);
                    }else {
                        jam1.setStatus(-1);
                    }
                }else {
                    jam1.setStatus(2);
                }
            }
            if (jam != null && jam.getStatus() !=null) {
                int status = jam.getStatus();
                for (int i = 0; i < jams.size(); i++) {
                    if (status != jams.get(i).getStatus()){
                        jams.remove(i);
                        i--;
                    }
                }
            }
        }
        PageHelper.startPage(page,size);
        return new PageInfo<>(jams);
    }

    @Override
    public void add(Jam jam) {
        jamMapper.insert(jam);
    }
}
