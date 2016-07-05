package com.elong.nb.common.model;

import com.google.gson.annotations.SerializedName;


/**
 * {"Version":1.11,"Local":"zh_CN","Request":{"ArrivalDate":
 * "2015-11-10T00:00:00+08:00","DepartureDate":"2015-11-11T00:00:00+08:00",
 * "CityId":"0101","QueryText":null,"QueryType":"intelligent","PaymentType":
 * "selfPay","ProductProperties":"all","Facilities":null,"StarRate":null,
 * "BrandId":null,"GroupId":0,"LowRate":0,"HighRate":0,"DistrictId":null,
 * "LocationId":null,"Position":null,"Sort":"default","PageIndex":8,"PageSize":
 * 20,"CustomerType":null,"ThemeIds":null,"ResultType":null},
 * "ProxyInfo":{
 * "UserName":"Test","AppKey":null,"UserGroup":1,"ProxyId":"A06","CardNo":18000,
 * "OrderFrom":50,"MemberLevel":"normal","SellChannel":"a","BookingChannel":
 * "onLine","SearchOrderType":"orderFrom","OrderContactType":"proxy",
 * "IsFilterSEMHotel":false,"EnabledPrepayProducts":true,
 * "EnabledPrepaySettlment":false,"PrepaySettlementRate":0.05,
 * "EnabledVirtualCardForPrepay":false,"EnabledVirtualCardForGuarantee":false,
 * "EnabledSpecialRate":false,"EnabledInstantConfirm":false,"EnabledInvoiceRole"
 * :false,"EnabledElongNoteReadRole":false,"EnabledCouponReadRole":true,
 * "EnableForcedGuaranteeOrder":true,"ClientIP":"127.0.0.1","EnableGroupCoupon":
 * false,"EnabledVirtualCardForGroup":false},
 * 
 * "Guid":
 * "67ff6456-5a5a-4909-8e17-2a2eb4d5f0bd"}
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class RestRequest<T>{
	
	@SerializedName(alternate="version", value = "Version")
	private Double Version;
	
	private EnumLocal Local = EnumLocal.zh_CN;
	private T Request;
	private ProxyAccount ProxyInfo;
	private String Guid;
	
	public String getGuid() {
		return Guid;
	}
	public void setGuid(String guid) {
		Guid = guid;
	}
	public Double getVersion() {
		return Version;
	}
	public void setVersion(Double version) {
		Version = version;
	}
	public EnumLocal getLocal() {
		return Local;
	}
	public void setLocal(EnumLocal local) {
		Local = local;
	}
	public T getRequest() {
		return Request;
	}
	public void setRequest(T request) {
		Request = request;
	}
	public ProxyAccount getProxyInfo() {
		return ProxyInfo;
	}
	public void setProxyInfo(ProxyAccount proxyInfo) {
		ProxyInfo = proxyInfo;
	}
	
}
