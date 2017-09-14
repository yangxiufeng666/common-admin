package com.common.system.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangxiufeng
 * @since 2017-09-14
 */
@TableName("rc_operation_log")
public class RcOperationLog extends Model<RcOperationLog> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 操作人ID
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 操作人姓名
     */
	private String username;
    /**
     * 被操作类
     */
	@TableField("class_name")
	private String className;
    /**
     * 方法
     */
	private String method;
    /**
     * 参数
     */
	private String args;
    /**
     * 原始数据
     */
	@TableField("origin_data")
	private String originData;
    /**
     * 新数据
     */
	@TableField("new_data")
	private String newData;
	@TableField("create_time")
	private Date createTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getOriginData() {
		return originData;
	}

	public void setOriginData(String originData) {
		this.originData = originData;
	}

	public String getNewData() {
		return newData;
	}

	public void setNewData(String newData) {
		this.newData = newData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RcOperationLog{" +
			"id=" + id +
			", userId=" + userId +
			", username=" + username +
			", className=" + className +
			", method=" + method +
			", args=" + args +
			", originData=" + originData +
			", newData=" + newData +
			", createTime=" + createTime +
			"}";
	}
}
