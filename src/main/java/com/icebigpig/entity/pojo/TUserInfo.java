package com.icebigpig.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


/**
 * @author <a href="mailto:icebigpig404@foxmail.com">icebigpig</a>
 * @version 1.0
 * date 2022/10/6 17:08
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TUserInfo {

    /**
     * 用户ID
     */
    @TableId
    private String uuid;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户简介
     */
    private String intro;

    /**
     * 个人网站
     */
    private String website;

    /**
     * 是否订阅
     */
    private Integer isSubscribe;

    /**
     * 是否禁用
     */
    private Integer isDisable;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

}