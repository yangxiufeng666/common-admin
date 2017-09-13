package com.common.system.entity;

import java.beans.Transient;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangxiufeng
 * @since 2017-09-12
 */
@TableName("rc_base_area")
public class RcBaseArea extends Model<RcBaseArea> {

    private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	@TableField("parent_code")
	private String parentCode;
	private Integer level;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;
	@TableField(exist=false)
	private List<RcBaseArea> children;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.code;
	}

	public List<RcBaseArea> getChildren() {
		return children;
	}

	public void setChildren(List<RcBaseArea> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "RcBaseArea{" +
			"code=" + code +
			", name=" + name +
			", parentCode=" + parentCode +
			", level=" + level +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}

}
