package com.qgq.fwpt.common.Enums;

/**
 * Created on 2017/04/07
 *
 * @author 繁华
 */
public enum UserRoleEnum {
    STUDENT(0, "学生"),
    TEACHER(1, "教师"),
    ADMIN(2, "管理员");
    private Integer code;
    private String desc;

    UserRoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "UserRoleEnum{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
