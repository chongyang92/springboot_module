package com.weboot.springboot.domain;

public class UserMenuKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_menu.USER_ID
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_menu.MENU_ID
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    private String menuId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_menu.USER_ID
     *
     * @return the value of user_menu.USER_ID
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_menu.USER_ID
     *
     * @param userId the value for user_menu.USER_ID
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_menu.MENU_ID
     *
     * @return the value of user_menu.MENU_ID
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_menu.MENU_ID
     *
     * @param menuId the value for user_menu.MENU_ID
     *
     * @mbg.generated Tue Feb 04 10:51:26 CST 2020
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}