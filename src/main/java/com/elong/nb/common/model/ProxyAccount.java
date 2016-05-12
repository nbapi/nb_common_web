package com.elong.nb.common.model;

import com.elong.nb.common.util.BigDecimalUtils;

public class ProxyAccount {
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
	  /// 是否过滤SEM关闭的酒店
	private boolean IsFilterSEMHotel;
    /// 是否开通了预付产品获取权限
	private boolean EnabledPrepayProducts;
    /// 是否开通了预付结算价权限
	private boolean EnabledPrepaySettlment;
    /// 预付结算价浮动比率，一般是0.05,0.08,0.10这样的; 新增支持数值大于3的时候直接加价的模式。
	private double PrepaySettlementRate;
  /// 是否开通了预付虚拟卡
	private boolean EnabledVirtualCardForPrepay;
    /// 是否开通了担保虚拟卡
	private boolean EnabledVirtualCardForGuarantee;
    /// 是否可以变价
	private boolean EnabledSpecialRate;
    /// 是否享受即时确认
	private boolean EnabledInstantConfirm;
    /// 是否有开发票权限
	private boolean EnabledInvoiceRole;
    /// 是否开通了艺龙备注权限。用于获取订单的时候是否返回艺龙备注
	private boolean EnabledElongNoteReadRole;
    /// 是否开通了搜索的前台现付的Coupon读取权限
	private boolean EnabledCouponReadRole;
    /// 是否开通了Coupon读取权限，成单的时候可以记录Coupon
	private boolean EnabledCouponRole;
    /// 仅使用哪些供应商的库存，用于库存全量接口过滤
	private String Supplier;
    /// 创建订单是否有权生成强制担保订单
	private boolean EnableForcedGuaranteeOrder;
    /// 是否忽略订单内容（入住日期、产品、客人名字等）的疑似重单校验
	private boolean EnableIgnoreCheckingDuplicatedOrder;
    /// 前台 现付价格是否给结算价(底价)
	private boolean EnableReturnAgentcyRateCost;
    /// 客户端IP地址
	private String ClientIP;
    /// 是否有团购Coupon权限
	private boolean EnableGroupCoupon;
    /// 是否开通了团购虚拟信用卡
	private boolean EnabledVirtualCardForGroup;
	private boolean IgnoreCheckGuestName;
	private boolean EnabledCommentReadRole;
	private String CommentUserKey;
	private Integer MaxDays = 180;/// 搜索、数据、订单使用的日期跨度最大天数
     
     
	/// 获取代理结算价
	/// <param name="costPrice">酒店底价</param>
	/// <param name="memberPrice">会员卖价</param>
	/// <returns></returns>
	public double getSettlementPrice(double costPrice, double memberPrice){
         if (EnabledPrepaySettlment || EnableReturnAgentcyRateCost){
             //酒店底价无效的时候尽量返回会员价作为结算价
             //NOTICE: 当底价低于5块的时候，这个产品可能是内部免费房，代理以会员价销售
             if (BigDecimalUtils.compareTo(costPrice,5) < 0 ){
                 if (BigDecimalUtils.compareTo(memberPrice,0) > 0){
                     return memberPrice;
                 }else{
                     return -1;
                 }
             }
             if (BigDecimalUtils.compareTo(memberPrice,0) <= 0 || BigDecimalUtils.compareTo(costPrice,memberPrice) >0){
                 return -1;
             }
             //佣金率大于40%，则是底价设置错误，代理以会员价销售
             if(BigDecimalUtils.compareTo(BigDecimalUtils.div(BigDecimalUtils.sub(memberPrice,costPrice), memberPrice),0.4) >0){
                 return memberPrice;
             }
             double price = memberPrice;
             //小于1的为按比率加价
             if (BigDecimalUtils.compareTo(PrepaySettlementRate, 0) >0 && BigDecimalUtils.compareTo(PrepaySettlementRate,1)<0){
                 if (BigDecimalUtils.compareTo(PrepaySettlementRate, 0.0001)<=0){
                     price = costPrice;
                 }else{
                	 price = Math.ceil(BigDecimalUtils.mul(BigDecimalUtils.add(PrepaySettlementRate, 1),costPrice));
                 }
             }else if(BigDecimalUtils.compareTo(PrepaySettlementRate,3)>0) {
            	 //大于3的为直接加价
            	 price = BigDecimalUtils.add(PrepaySettlementRate, costPrice);
             }
             //防止价格上浮后，出现结算价过高
             if (BigDecimalUtils.compareTo(price,memberPrice) >0) 
            	 price = memberPrice;
             return price;
         }else{
             return -1;
         }
     }

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
	public Boolean getEnabledInstantConfirm() {
		return EnabledInstantConfirm;
	}
	public void setEnabledInstantConfirm(Boolean enabledInstantConfirm) {
		EnabledInstantConfirm = enabledInstantConfirm;
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
	public Boolean getEnableGroupCoupon() {
		return EnableGroupCoupon;
	}
	public void setEnableGroupCoupon(Boolean enableGroupCoupon) {
		EnableGroupCoupon = enableGroupCoupon;
	}
	public Boolean getEnabledVirtualCardForGroup() {
		return EnabledVirtualCardForGroup;
	}
	public void setEnabledVirtualCardForGroup(Boolean enabledVirtualCardForGroup) {
		EnabledVirtualCardForGroup = enabledVirtualCardForGroup;
	}
	public Boolean getIgnoreCheckGuestName() {
		return IgnoreCheckGuestName;
	}
	public void setIgnoreCheckGuestName(Boolean ignoreCheckGuestName) {
		IgnoreCheckGuestName = ignoreCheckGuestName;
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
    
}
