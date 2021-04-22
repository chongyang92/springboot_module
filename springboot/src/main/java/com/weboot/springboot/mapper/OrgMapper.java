package com.weboot.springboot.mapper;

import com.weboot.springboot.model.Org;
import com.weboot.springboot.model.OrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    long countByExample(OrgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    int deleteByExample(OrgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    int deleteByPrimaryKey(String orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    int insert(Org record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    int insertSelective(Org record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    List<Org> selectByExample(OrgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    Org selectByPrimaryKey(String orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    int updateByExampleSelective(@Param("record") Org record, @Param("example") OrgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    int updateByExample(@Param("record") Org record, @Param("example") OrgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    int updateByPrimaryKeySelective(Org record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbg.generated Thu Jan 16 15:18:59 CST 2020
     */
    int updateByPrimaryKey(Org record);
}