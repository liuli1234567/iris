package com.zhongke.service;

import com.github.pagehelper.PageInfo;
import com.zhongke.pojo.MemberGrade;

public interface MemberGradeService {
    PageInfo<MemberGrade> memGrades(MemberGrade memberGrade, int page, int size);

    void update(MemberGrade memberGrade);

    void add(MemberGrade memberGrade);

    void delete(int id);
}
