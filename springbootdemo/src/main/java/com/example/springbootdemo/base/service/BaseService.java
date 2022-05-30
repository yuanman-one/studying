package com.example.springbootdemo.base.service;

public interface BaseService<Model> {

    /**
     * 根据ID删除记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除
     */
    int deleteByPrimaryKey(Integer ID);

    /**
     * 添加完整字段记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除
     */
    int insert(Model record);

    /**
     * 添加记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除
     */
    int insertSelective(Model record);

    /**
     * 根据ID查询记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除
     */
    Model selectByPrimaryKey(Integer ID);

    /**
     * 根据ID修改记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除
     */
    int updateByPrimaryKeySelective(Model record);

    /**
     * 根据ID修改完整字段记录
     *
     * @mbg.generated 自动生成的标识，如修改请删除
     */
    int updateByPrimaryKey(Model record);
}
