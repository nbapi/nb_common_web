package com.elong.nb.common.model;

import com.elong.nb.common.util.BigDecimalUtils;

public class ProxyAccount {

	/** 
	 * 佣金过滤－现付等级
	 *
	 * EnumAgencyLevel ProxyAccount.java AgencyCommisionLevel
	 */
	private EnumAgencyLevel AgencyCommisionLevel;

	/** 
	 * 佣金过滤－预付等级
	 *
	 * EnumPrepayLevel ProxyAccount.java PrepayCommisionLevel
	 */
	private EnumPrepayLevel PrepayCommisionLevel;

	private String UserName;
	private String AppKey;
	private int UserGroup;// API账户分组
	private String ProxyId;// 代理编号
	private long CardNo;// 会员卡号
	private int OrderFrom;// 订单来源
	private EnumMemberLevel MemberLevel;// 会员等级
	private EnumSellChannel SellChannel;// 销售渠道
	private EnumBookingChannel BookingChannel;// 预订渠道
	private EnumOrderType SearchOrderType;// 订单搜索方式
	private EnumOrderContactType OrderContactType;// 订单处理联系方式，0---不处理，1---联系客人，2---联系代理
	// / 是否过滤SEM关闭的酒店
	private boolean IsFilterSEMHotel;
	// / 是否开通了预付产品获取权限
	private boolean EnabledPrepayProducts;
	// / 是否开通了预付结算价权限
	private boolean EnabledPrepaySettlment;
	// / 预付结算价浮动比率，一般是0.05,0.08,0.10这样的; 新增支持数值大于3的时候直接加价的模式。
	// 进行了升级，0~1是之前的老规则；[1,2) 是过渡规则，过渡规则的小数点按老规则校验，但按新规则计算；[2, +*)是新规则校验、计算
	//2017.2.23后 此字段功能拆分，只做加价率使用。取值范围 0~1
	private double PrepaySettlementRate;
	//预付定价模式：1、老定价模式(默认) 2、兼容模式 3、新定价模式 4、原始底价模式
	private int PrepaySettlementRateMode=1;
	// / 是否开通了预付虚拟卡
	private boolean EnabledVirtualCardForPrepay;
	// / 是否开通了担保虚拟卡
	private boolean EnabledVirtualCardForGuarantee;
	// / 是否可以变价
	private boolean EnabledSpecialRate;
	// / 是否有开发票权限
	private boolean EnabledInvoiceRole;
	// / 是否开通了艺龙备注权限。用于获取订单的时候是否返回艺龙备注
	private boolean EnabledElongNoteReadRole;
	// / 是否开通了搜索的前台现付的Coupon读取权限
	private boolean EnabledCouponReadRole;
	// / 是否开通了Coupon读取权限，成单的时候可以记录Coupon
	private boolean EnabledCouponRole;
	// / 仅使用哪些供应商的库存，用于库存全量接口过滤
	private String Supplier;
	// / 创建订单是否有权生成强制担保订单
	private boolean EnableForcedGuaranteeOrder;	//！！！成单在读，可能是expedia在传
	// / 是否忽略订单内容（入住日期、产品、客人名字等）的疑似重单校验
	private boolean EnableIgnoreCheckingDuplicatedOrder;  //！！！成单在读，可能是expedia在传
	// / 前台 现付价格是否给结算价(底价)
	private boolean EnableReturnAgentcyRateCost;
	// / 客户端IP地址
	private String ClientIP;			//!!!特别，网关塞进来的！！
	private boolean EnabledCommentReadRole;
	private String CommentUserKey;
	private Integer MaxDays = 180;// / 搜索、数据、订单使用的日期跨度最大天数	！！！！！没人传，考虑后期变量化

	private int IntegerPriceType; // 进行整形价格处理类型：0=不处理,1=Round,2=Ceil

	private boolean IsOnlyStraight; // 是否过滤非直签
	/**
	 * 最低价格利润率，默认0不限制，输入5表示过滤没有5%的利润的价格
	 */
	private double LowestProfitPercent;
	
	private boolean IsCustomerPriceIntoSalePrice;

	private String ProjectName;
	
	private String SecondSecretKey;
	
	private boolean UniqueSearchByOrderFrom;//主要用于OrderList接口，通过OrderFrom做唯一检索，可以检测到其他渠道产生的订单。
	
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getAppKey() {
		return AppKey;
	}

	public void setAppKey(String appKey) {
		AppKey = appKey;
	}

	public Integer getUserGroup() {
		return UserGroup;
	}

	public void setUserGroup(Integer userGroup) {
		UserGroup = userGroup;
	}

	public String getProxyId() {
		return ProxyId;
	}

	public void setProxyId(String proxyId) {
		ProxyId = proxyId;
	}

	public Long getCardNo() {
		return CardNo;
	}

	public void setCardNo(Long cardNo) {
		CardNo = cardNo;
	}

	public Integer getOrderFrom() {
		return OrderFrom;
	}

	public void setOrderFrom(Integer orderFrom) {
		OrderFrom = orderFrom;
	}

	public EnumMemberLevel getMemberLevel() {
		return MemberLevel;
	}

	public void setMemberLevel(EnumMemberLevel memberLevel) {
		MemberLevel = memberLevel;
	}

	public EnumSellChannel getSellChannel() {
		return SellChannel;
	}

	public void setSellChannel(EnumSellChannel sellChannel) {
		SellChannel = sellChannel;
	}

	public EnumBookingChannel getBookingChannel() {
		return BookingChannel;
	}

	public void setBookingChannel(EnumBookingChannel bookingChannel) {
		BookingChannel = bookingChannel;
	}

	public EnumOrderType getSearchOrderType() {
		return SearchOrderType;
	}

	public void setSearchOrderType(EnumOrderType searchOrderType) {
		SearchOrderType = searchOrderType;
	}

	public EnumOrderContactType getOrderContactType() {
		return OrderContactType;
	}

	public void setOrderContactType(EnumOrderContactType orderContactType) {
		OrderContactType = orderContactType;
	}

	public Boolean getIsFilterSEMHotel() {
		return IsFilterSEMHotel;
	}

	public void setIsFilterSEMHotel(Boolean isFilterSEMHotel) {
		IsFilterSEMHotel = isFilterSEMHotel;
	}

	public Boolean getEnabledPrepayProducts() {
		return EnabledPrepayProducts;
	}

	public void setEnabledPrepayProducts(Boolean enabledPrepayProducts) {
		EnabledPrepayProducts = enabledPrepayProducts;
	}

	public Boolean getEnabledPrepaySettlment() {
		return EnabledPrepaySettlment;
	}

	public void setEnabledPrepaySettlment(Boolean enabledPrepaySettlment) {
		EnabledPrepaySettlment = enabledPrepaySettlment;
	}

	public Double getPrepaySettlementRate() {
		return PrepaySettlementRate;
	}

	public void setPrepaySettlementRate(Double prepaySettlementRate) {
		PrepaySettlementRate = prepaySettlementRate;
	}

	public Boolean getEnabledVirtualCardForPrepay() {
		return EnabledVirtualCardForPrepay;
	}

	public void setEnabledVirtualCardForPrepay(Boolean enabledVirtualCardForPrepay) {
		EnabledVirtualCardForPrepay = enabledVirtualCardForPrepay;
	}

	public Boolean getEnabledVirtualCardForGuarantee() {
		return EnabledVirtualCardForGuarantee;
	}

	public void setEnabledVirtualCardForGuarantee(Boolean enabledVirtualCardForGuarantee) {
		EnabledVirtualCardForGuarantee = enabledVirtualCardForGuarantee;
	}

	public Boolean getEnabledSpecialRate() {
		return EnabledSpecialRate;
	}

	public void setEnabledSpecialRate(Boolean enabledSpecialRate) {
		EnabledSpecialRate = enabledSpecialRate;
	}

	public Boolean getEnabledInvoiceRole() {
		return EnabledInvoiceRole;
	}

	public void setEnabledInvoiceRole(Boolean enabledInvoiceRole) {
		EnabledInvoiceRole = enabledInvoiceRole;
	}

	public Boolean getEnabledElongNoteReadRole() {
		return EnabledElongNoteReadRole;
	}

	public void setEnabledElongNoteReadRole(Boolean enabledElongNoteReadRole) {
		EnabledElongNoteReadRole = enabledElongNoteReadRole;
	}

	public Boolean getEnabledCouponReadRole() {
		return EnabledCouponReadRole;
	}

	public void setEnabledCouponReadRole(Boolean enabledCouponReadRole) {
		EnabledCouponReadRole = enabledCouponReadRole;
	}

	public Boolean getEnabledCouponRole() {
		return EnabledCouponRole;
	}

	public void setEnabledCouponRole(Boolean enabledCouponRole) {
		EnabledCouponRole = enabledCouponRole;
	}

	public String getSupplier() {
		return Supplier;
	}

	public void setSupplier(String supplier) {
		Supplier = supplier;
	}

	public Boolean getEnableForcedGuaranteeOrder() {
		return EnableForcedGuaranteeOrder;
	}

	public void setEnableForcedGuaranteeOrder(Boolean enableForcedGuaranteeOrder) {
		EnableForcedGuaranteeOrder = enableForcedGuaranteeOrder;
	}

	public Boolean getEnableIgnoreCheckingDuplicatedOrder() {
		return EnableIgnoreCheckingDuplicatedOrder;
	}

	public void setEnableIgnoreCheckingDuplicatedOrder(Boolean enableIgnoreCheckingDuplicatedOrder) {
		EnableIgnoreCheckingDuplicatedOrder = enableIgnoreCheckingDuplicatedOrder;
	}

	public Boolean getEnableReturnAgentcyRateCost() {
		return EnableReturnAgentcyRateCost;
	}

	public void setEnableReturnAgentcyRateCost(Boolean enableReturnAgentcyRateCost) {
		EnableReturnAgentcyRateCost = enableReturnAgentcyRateCost;
	}

	public String getClientIP() {
		return ClientIP;
	}

	public void setClientIP(String clientIP) {
		ClientIP = clientIP;
	}

	public Boolean getEnabledCommentReadRole() {
		return EnabledCommentReadRole;
	}

	public void setEnabledCommentReadRole(Boolean enabledCommentReadRole) {
		EnabledCommentReadRole = enabledCommentReadRole;
	}

	public String getCommentUserKey() {
		return CommentUserKey;
	}

	public void setCommentUserKey(String commentUserKey) {
		CommentUserKey = commentUserKey;
	}

	public Integer getMaxDays() {
		return MaxDays;
	}

	public void setMaxDays(Integer maxDays) {
		MaxDays = maxDays;
	}

	public int getIntegerPriceType() {
		return IntegerPriceType;
	}

	public void setIntegerPriceType(int integerPriceType) {
		IntegerPriceType = integerPriceType;
	}

	public boolean isIsOnlyStraight() {
		return IsOnlyStraight;
	}

	public void setIsOnlyStraight(boolean isOnlyStraight) {
		IsOnlyStraight = isOnlyStraight;
	}

	/**   
	 * 得到isCustomerPriceIntoSalePrice的值   
	 *   
	 * @return isCustomerPriceIntoSalePrice的值
	 */
	public boolean isIsCustomerPriceIntoSalePrice() {
		return IsCustomerPriceIntoSalePrice;
	}

	/**
	 * 设置isCustomerPriceIntoSalePrice的值
	 *   
	 * @param isCustomerPriceIntoSalePrice 被设置的值
	 */
	public void setIsCustomerPriceIntoSalePrice(boolean isCustomerPriceIntoSalePrice) {
		IsCustomerPriceIntoSalePrice = isCustomerPriceIntoSalePrice;
	}
	
	public double getLowestProfitPercent() {
		return LowestProfitPercent;
	}

	public void setLowestProfitPercent(double lowestProfitPercent) {
		LowestProfitPercent = lowestProfitPercent;
	}

	/**   
	 * 得到AgencyCommisionLevel的值   
	 *   
	 * @return AgencyCommisionLevel的值
	 */
	public EnumAgencyLevel getAgencyCommisionLevel() {
		return AgencyCommisionLevel;
	}

	/**
	 * 设置AgencyCommisionLevel的值
	 *   
	 * @param AgencyCommisionLevel 被设置的值
	 */
	public void setAgencyCommisionLevel(EnumAgencyLevel AgencyCommisionLevel) {
		this.AgencyCommisionLevel = AgencyCommisionLevel;
	}

	/**   
	 * 得到PrepayCommisionLevel的值   
	 *   
	 * @return PrepayCommisionLevel的值
	 */
	public EnumPrepayLevel getPrepayCommisionLevel() {
		return PrepayCommisionLevel;
	}

	/**
	 * 设置PrepayCommisionLevel的值
	 *   
	 * @param PrepayCommisionLevel 被设置的值
	 */
	public void setPrepayCommisionLevel(EnumPrepayLevel PrepayCommisionLevel) {
		this.PrepayCommisionLevel = PrepayCommisionLevel;
	}

	/**
	 * @return the prepaySettlementRateMode
	 */
	public int getPrepaySettlementRateMode() {
		return PrepaySettlementRateMode;
	}

	/**
	 * @param prepaySettlementRateMode the prepaySettlementRateMode to set
	 */
	public void setPrepaySettlementRateMode(int prepaySettlementRateMode) {
		PrepaySettlementRateMode = prepaySettlementRateMode;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getSecondSecretKey() {
		return SecondSecretKey;
	}

	public void setSecondSecretKey(String secondSecretKey) {
		SecondSecretKey = secondSecretKey;
	}

	public boolean isUniqueSearchByOrderFrom() {
		return UniqueSearchByOrderFrom;
	}

}
