package com.example.springbootdemo.dao;

import com.example.springbootdemo.model.SchoolClass;

public interface SchoolClassMapper {

    /**
     * 根据ID删除记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    int deleteByPrimaryKey(Integer ID);

    /**
     * 添加完整字段记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    int insert(SchoolClass record);

    /**
     * 添加记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    int insertSelective(SchoolClass record);

    /**
     * 根据ID查询记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    SchoolClass selectByPrimaryKey(Integer ID);

    /**
     * 根据ID修改记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    int updateByPrimaryKeySelective(SchoolClass record);

    /**
     * 根据ID修改完整字段记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    int updateByPrimaryKey(SchoolClass record);
}
