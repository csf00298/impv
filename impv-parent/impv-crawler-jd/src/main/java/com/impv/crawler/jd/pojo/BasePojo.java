package com.impv.crawler.jd.pojo;

import java.util.Date;

/**
 * 淘淘商城pojo基类
 * 
 * @author zhijun
 *
 */
public abstract class BasePojo {
	
	/**
	 * 创建时间
	 */
	protected Date created;
	
	/**
	 * 更新时间
	 */
	protected Date updated;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
