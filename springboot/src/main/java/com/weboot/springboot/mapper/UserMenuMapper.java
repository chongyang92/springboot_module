package com.weboot.springboot.mapper;

import com.weboot.springboot.domain.UserMenuExample;
import com.weboot.springboot.domain.UserMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_menu
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    long countByExample(UserMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_menu
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    int deleteByExample(UserMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_menu
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    int deleteByPrimaryKey(UserMenuKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_menu
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    int insert(UserMenuKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_menu
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    int insertSelective(UserMenuKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_menu
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    List<UserMenuKey> selectByExample(UserMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_menu
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserMenuKey record, @Param("example") UserMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_menu
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    int updateByExample(@Param("record") UserMenuKey record, @Param("example") UserMenuExample example);
}