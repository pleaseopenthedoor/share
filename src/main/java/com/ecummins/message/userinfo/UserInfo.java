package com.ecummins.message.userinfo;

import com.ecummins.message.type.IdType;

/**
 * 用户信息
 */
public class UserInfo {
	
	/**
	 * 用户id
	 * {@link IdType#USER_ID}
	 * 如果指定的ID类型为USER_ID时，此值不能为空
	 */
	private Long userId;
	
	
	/**
	 * 用户所属服务站id
	 * {@link IdType#SERVICE_STATION_ID}
	 * 如果指定的ID类型为SERVICE_STATION_ID时，此值不能为空
	 */
	private Long serviceStationId;
	
	
	/**
	 *  主技师对应的服务工单ID
	 * {@link IdType#SERVICE_WORK_ORDER_ID_PRINCIPAL_TECHNICIAN}
	 * 如果指定的ID类型为SERVICE_WORK_ORDER_ID_PRINCIPAL_TECHNICIAN时，此值不能为空
	 */
	private Long serviceWorkOrderIdOfPrincipalTechnician;
	
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 消息推送ClientId
	 */
	private String clientId;
	
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getServiceStationId() {
		return serviceStationId;
	}

	public void setServiceStationId(Long serviceStationId) {
		this.serviceStationId = serviceStationId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Long getServiceWorkOrderIdOfPrincipalTechnician() {
		return serviceWorkOrderIdOfPrincipalTechnician;
	}

	public void setServiceWorkOrderIdOfPrincipalTechnician(Long serviceWorkOrderIdOfPrincipalTechnician) {
		this.serviceWorkOrderIdOfPrincipalTechnician = serviceWorkOrderIdOfPrincipalTechnician;
	}
	
}
