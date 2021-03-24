package com.ecummins.message.type;

/**
 * ID类型
 */
public enum IdType {

	/**
	 * 服务站ID
	 * 根据服务站ID查询服务站站长/信息员信息
	 */
	SERVICE_STATION_ID(1),

	/**
	 * userID
	 * 根据用户ID查询出用户信息
	 */
	USER_ID(2),
	
	/**
	 * 服务工单ID
	 * 根据服务工单ID查询出工单对应的主技师
	 */
	SERVICE_WORK_ORDER_ID_PRINCIPAL_TECHNICIAN(3);

	private int code;
	

	IdType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
