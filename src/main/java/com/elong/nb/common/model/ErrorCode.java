/**   
 * @(//)ErrorCode.java	2016年8月23日	下午6:00:19	   
 *     
 * Copyrights (C) 2016艺龙旅行网保留所有权利
 */
package com.elong.nb.common.model;

/**
 * 错误编码信息
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2016年8月23日 下午6:00:19   suht     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		suht  
 * @version		1.0  
 * @since		JDK1.7
 */
public interface ErrorCode {

	// 错误码编码规则
	/*
	 * 第一部分：错误码 必须唯一
	 * 
	 * 建议规则： 第1位：H， 表示是酒店 第2位：0或1， 表示是否可以重试 第3-4位：01-99 模块编号 第5-6位：01-99 文件代码编号 第7-8位：01-99 代码方法编号 第9-11位：000-999 上述编码无法唯一时候增加的顺序号
	 * 
	 * 第二部分：分割竖线
	 * 
	 * 第三部分：错误详情 可以采用 {0} {1} 这样的定位符，后记日志的时候可以写入一些特定值 通用 H000001 订单 H001001 搜索 H002001 增量 H003001 数据 H004001
	 */

	public static final String Success = "0";

	// region 通用 H000001
	public static final String Common_HotelIdRequired = "H000001|酒店编号必须填写";
	public static final String Common_RoomTypeIdRequired = "H000002|房型编号必须填写";
	public static final String Common_RatePlanIdRequired = "H000003|产品编号必须填写";
	public static final String Common_EmailFormatError = "H000004|电子邮件格式错误";
	public static final String Common_ArrivalDateRequired = "H000005|入住日期不能为空";
	public static final String Common_DepartureDateRequired = "H000006|离店日期不能为空";
	public static final String Common_HotelCityIdRequired = "H000007|城市Id不能为空";
	public static final String Common_IdsFormatWrong = "H000008|Ids不符合数字逗号格式";
	public static final String Common_IdsMustLestThanTen = "H000009|Ids个数必须小于20个";
	public static final String Common_NumberIdsFormatErrorAndLessThanTen = "H000010|Ids格式错误且个数必须小于等于10个";
	public static final String Common_PaymentTypeRequired = "H000011|付款类型必须填写";
	public static final String Common_PaymentTypeInvalid = "H000012|付款类型错误";
	public static final String Common_StartDateRequired = "H000013|开始时间必须填写";
	public static final String Common_EndDateRequired = "H000014|酒店结束时间必须填写";
	public static final String Common_StartDateLessThanEndDate = "H000015|开始日期必须小于等于结束日期";

	public static final String Common_DataRequired = "H000016|Data参数必须填写";
	public static final String Common_GUIDRequired = "H000017|GUID参数必须填写";
	public static final String Common_InvalidChar = "H000018|{0}含有非法字符";

	public static final String Common_HotelCodeRequired = "H000019|HotelCode必须填写";
	public static final String Common_VersionToLow = "H000020|请求版本号version太低，要求{0}以上";
	public static final String Common_MaxLength = "H000021|{0}最大长度:{1}";
	public static final String Common_HotelIdRequiredOnlyOne = "H000022|HotelCodes输入的时候，HotelIds只能是它们对应的一个酒店";
	public static final String Common_RatePlanInvalid = "H000023|RatePlan无效";

	public static final String Common_VersionInvalid = "H000024|Version不能为空";
	public static final String Common_ProxyInfoInvalid = "H000025|企业账户信息不能为空";
	public static final String Common_NumberCodesFormatErrorAndLessThanTen = "H000010|Codes格式错误且个数必须小于等于10个";
	public static final String Common_ParamInvalid = "H000996|请求参数错误,请检查";
	public static final String Common_UnkownException = "H000997|未知异常: ";
	public static final String Common_RequesstParameterException = "H000998|请求参数异常";
	public static final String Common_SystemException = "H100999|系统异常: ";
	// endregion

	// region 订单 H001001
	public static final String Order_AffiliateConfirmationIdRequired = "H001001|订单确认号必须填写";
	public static final String Order_ArrivalDateRequired = "H001002|入住时间必须填写";
	public static final String Order_DepartureDateRequired = "H001003|离店时间必须填写";
	public static final String Order_CustomerTypeRequired = "H001004|客人类型必须填写";
	public static final String Order_PaymentTypeRequired = "H001005|付款类型必须填写";

	public static final String Order_TotalPriceRangeInvalid = "H001006|订单总价大于0";
	public static final String Order_NumberOfCustomersRequired = "H001007|客人数量必须填写";
	public static final String Order_EarliestArrivalTimeRequired = "H001008|最早到店时间必须填写";
	public static final String Order_LatestArrivalTimeRequired = "H001009|最晚到店时间必须填写";
	public static final String Order_CurrencyCodeRequired = "H001010|货币类型必须填写";
	public static final String Order_TotalPriceRequired = "H001011|总价必须填写";
	public static final String Order_CustomerIPAddressRequired = "H001012|客人访问IP必须填写";
	public static final String Order_IsGuaranteeOrChargedRequired = "H001013|是否已担保或已付款必须填写";
	public static final String Order_SupplierCardNoRequired = "H001014|供应商卡号必须填写";
	public static final String Order_IsNeedInvoiceRequired = "H001015|是否需要发票必须填写";

	public static final String Order_CustomerRequired = "H001016|客人信息必须填写";
	public static final String Order_CustomerNumberGreaterThanRoom = "H001017|客人数不能小于房间数";
	public static final String Order_ArrivalDateLessThanDepartureDate = "H001018|入住日期不能早于当前日期且不能大于离店日期";
	public static final String Order_ContactRequired = "H001019|联系人信息必须填写";
	public static final String Order_ContactNameRequired = "H001020|姓名必须填写";
	public static final String Order_ContactInfoRequired = "H001021|邮箱、手机、电话、传真至少填写一项";
	public static final String Order_GenderRequired = "H001022|性别必须填写";

	public static final String Order_ArrivalTimeEarlyLessThanLates = "H001023|最早到店时间必须小于最晚到店时间";
	public static final String Order_ArrivalTimeEarlyLessThanArrivalDate = "H001024|最早到店时间必须大于到店时间";
	public static final String Order_ArrivalTimeEarlyGraterThanNow = "H001025|最早或最晚到店时间必须大于当前时间";
	public static final String Order_ArrivalTimeEarlyRangeError = "H001026|最早到店时间范围是入住日6:00-23:59";
	public static final String Order_ArrivalTimeLatestRangeError = "H001027|最晚到店时间范围是入住日7:00-23:59和次日1:00-6:00";
	public static final String Order_ArrivalTimeMustPoint = "H001028|到店时间必须是整点、半点、或23:59";
	public static final String Order_ArrivalTimeInterval = "H001029|最晚到店时间和最早到店时间间隔建议不大于3小时";

	public static final String Order_CreditCardNumberRequired = "H001030|信用卡号必须填写";
	public static final String Order_CreditCardCVVRequired = "H001031|信用卡3位校验码必须填写";
	public static final String Order_CreditCardCVVMustNull = "H001031|信用卡3位校验码无需填写";
	public static final String Order_CreditCardExpirationYearRequired = "H001032|信用卡有效年错误";
	public static final String Order_CreditCardExpirationMonthRequired = "H001033|信用卡有效月错误";
	public static final String Order_CreditCardHolderNameRequired = "H001034|信用卡持卡人必须填写";
	public static final String Order_CreditCardIdTypeRequired = "H001035|信用卡证件类型必须填写";
	public static final String Order_CreditCardIdNoRequired = "H001036|信用卡证件号码必须填写";
	public static final String Order_CreditCardVcardIsInValid = "H001037|信用卡失效或已担保或已支付业务已经失效";
	public static final String Order_PrepayOrderCreditCardRequired = "H001038|预付或强制担保订单信用卡信息必须填写";
	public static final String Order_PrepayOrderPaymentInfoRequired = "H001038-1|预付或强制担保订单支付信息必须填写";
	public static final String Order_PrepayOrderMustIsGurantedOrCharged = "H001039|预付或强制担保订单必须设置为已经担保或已经支付";
	public static final String Order_NoVcardRightCanNotSetPay = "H001040|无支付或担保权限,不能设置为已经担保或已经支付";
	public static final String Order_PrepayOrderCreditCardNoNeed = "H001041|使用已担保或已支付业务，不需要提供信用卡信息";
	public static final String Order_SelfPayCreditCardnoNeed = "H001042|非担保、非预付订单无需提供信用卡信息";

	public static final String Order_SubmitRepeatORTooFast = "H001043|订单重复或过快提交";
	public static final String Order_SubmitOrderFail = "H001044-{0}|下单失败,{1}";
	public static final String Order_AboutSameOrder = "H001045|疑似重复订单，不进行处理";

	public static final String Order_NotEnableInvoice = "H001046|系统不能接受贵司的发票请求";
	public static final String Order_InvoiceRequired = "H001047|需要开票据的时候，票据信息必须提供";
	public static final String Order_InvoiceRecipientRequired = "H001048|需要开票据的时候，票据收件信息必须提供";
	public static final String Order_InvoiceRecipientAddressRequired = "H001049|需要开票据的时候，票据收件地址信息必须提供";
	public static final String Order_InvoiceRecipientNameRequired = "H001050|需要开票据的时候，票据收件联系人必须提供";
	public static final String Order_InvoiceRecipientPhoneRequired = "H001051|需要开票据的时候，票据收件联系信息必须提供";
	public static final String Order_InvoiceAmountGraterThanMemberPrice = "H001052|需要开票据的时候，发票金额不能高于会员价(Member)总和";
	public static final String Order_InvoiceNotElong = "H001052|非艺龙开发票,不能提交发票信息";
	public static final String Order_OrderIdRequired = "H001053|订单号必须填写";
	public static final String Order_OrderIDError = "H001054|订单ID错误，订单号不存在";
	public static final String Order_OrderFromError = "H001055|订单ID错误! OrderFrom不一致";
	public static final String Order_InvoiceAmountZero = "H001056|需要开票据的时候，发票金额不能为0";

	public static final String Order_OrderIsCancelled = "H001056|订单已经处于取消状态";
	public static final String Order_OutOfLastCancelTime = "H001057|已超过最晚取消时间";
	public static final String Order_CreateOrderTimeRangeInvalid = "H001058|订单的创建时间范围无效";
	public static final String Order_OrderArrivalDateRangeInvalid = "H001059|订单的到店时间范围无效";
	public static final String Order_OrderDepartureDateRangeInvalid = "H001060|订单的离店时间范围无效";
	public static final String Order_OnlyNVAStatusCanModify = "H001061|仅新单、已审和已确认、满房、特殊满房五个状态可以修改订单";
	public static final String Order_PrepayCanNotModify = "H001062|预付订单不能修改";
	public static final String Order_GuaranteeCanNotModify = "H001063|担保订单不能修改";
	public static final String Order_OutOfLastModifyTime = "H001064|已经超过最晚修改订单时间";
	public static final String Order_RoomFull = "H001065|满房";
	public static final String Order_RoomCountNotEnough = "H001066|房量不够 ";
	public static final String Order_RoomNotInBookingDate = "H001067|房间不在可预订时间内 ";
	public static final String Order_RoomStatusIsDisable = "H001068|房态不可用";
	public static final String Order_RoomNumbersRangeInvalid = "H001069|房间数量范围1~10";
	public static final String Order_RoomsRequired = "H001070|房间必须填写";
	public static final String Order_NumberOfRoomsRequired = "H001071|房间数量必须填写";
	public static final String Order_RoomsNumberNotEqealRoomsCount = "H001072|房间数量和实际房间数不相同";

	public static final String Order_BaseNightlyRateDateRequired = "H001073|多天价格日期必须填写";
	public static final String Order_BaseNightlyRateMemberRequired = "H001074|多天价格会员价必须填写";
	public static final String Order_BaseNightlyRateCostRequired = "H001075|多天价格结算价必须填写";
	public static final String Order_BaseNightlyRateMemberError = "H001076|多天价格会员价格错误";
	public static final String Order_BaseNightlyRateCountError = "H001077|多天会员价格数量错误";
	public static final String Order_BaseNightlyRateDateOrderError = "H001078|多天会员价日期顺序错误";
	public static final String Order_BaseNightlyRateCostError = "H001079|多天会员价格底价错误";
	public static final String Order_BaseNightlyRateMemberTooLow = "H001080|多天会员会员价过低";
	public static final String Order_BaseNightlyRateCostTooLow = "H001081|多天会员结算价过低";
	public static final String Order_BaseNightlyRateTotalPriceError = "H001082|多天会员总价不一致";

	public static final String Order_HotelNotInService = "H001083|获取产品信息失败，可能酒店、房型和RatePlan无效或未关联，也可能无对应库存或房价";
	public static final String Order_ObjectRelationError = "H001083-0|对象关系错误";
	public static final String Order_ObjectStatusInvalid = "H001083-1|对象状态无效";

	public static final String Order_TotalPriceInvalid = "H001084|总价错误,应该为";

	public static final String Order_SubmitOrderException = "H001085|底层提交订单异常";

	public static final String Order_LastLimitProductBeSaledinFitTime = "H001086|尾房产品应该在合适的时间销售";

	public static final String Order_CheckInFeedBackRoomNoError = "H001087|房号长度不能大于50";
	public static final String Order_CheckInFeedBackNotesError = "H001088|备注长度不能大于500";
	public static final String Order_CheckInFeedBackGuestNameError = "H001089|客人姓名长度不能大于50";
	public static final String Order_CheckInFeedBackRNGError = "H001090|房号、客人姓名、备注不能同时为空";
	public static final String Order_CheckInFeedBackError = "H001091|订单反馈失败";

	public static final String Order_OrderIdsConditionError = "H001092|订单号和订单确认号不能同时为空";
	public static final String Order_SubmitOrderSaveFailed = "H101093|底层提交订单保存异常,请重试";

	public static final String Order_CancelFailedInWCF = "H001094|取消订单失败:";
	public static final String Order_InvalidWordInNoteToHotel = "H001095|给酒店的备注不能包含：务必、一定、必须此类话语";
	public static final String Order_CustomerType = "H001096|宾客类型错误";

	public static final String Order_InvlidateGusetName = "H001097|客人姓名未通过校验";
	public static final String Order_ErrorOrderRetryOverLimit = "H001098|订单错误重试次数不能超过{0}次";
	public static final String Order_CreditCardInvalid = "H001099|信用卡验证失败：";
	public static final String Order_CreditCardRequired = "H001100|需要提供信用卡";

	public static final String Order_PaymentIsNotPayable = "H001101-0|当前订单不能支付或不需要支付";
	public static final String Order_PaymentAmountIsLess = "H001102|支付金额小于应支付金额￥{0}";

	public static final String Order_QueryOrderError = "H001103|查询订单信息错误，请重试";
	public static final String Order_QueryOrderInfoError = "H001104|查询订单处理信息错误，请重试";
	public static final String Order_PushOrderConfirmUnAllowed = "H001105|当前订单不接受催确认请求";
	public static final String Order_PushOrderConfirmTooFrquently = "H001106|催确认太频繁，请等{0}秒后重试";
	public static final String Order_PushOrderConfirmError = "H001107|催确认出现错误：{0}";
	public static final String Order_PaymentIsNotNewFlow = "H001108|该订单不是新流程订单不能继续支付，请成单接口使用大于等于v1.19的版本";

	public static final String Order_Coupon_MustLessThan = "H001109|传入的Coupon值不能大于{0}";
	public static final String Order_RPNotInService = "H001110|RatePlan不在服务时间内";
	public static final String Order_CustomerPriceInvalid = "H001111|卖给客人的价格异常";
	public static final String Order_CustomerPriceRequried = "H001112|卖给客人的价格必填";
	public static final String Order_CurrencyCodeIsMismatched = "H001113|货币类型(CurrencyCode)应该为{0}";
	public static final String Order_BreakfastCount = "H001114|日期：{0}的早餐份数应该为{1} ";
	public static final String Order_CanCleTime = "H001115|取消时间晚于最晚取消时间，最晚取消时间为{0}";
	public static final String Order_GuaranteeAmount = "H001116|担保金额小于最小担保金额，最少担保金额应为{0}";
	public static final String Order_CanNotCancel = "H001117|取消时间不允许变更，取消";
	public static final String Order_CanCleTimeIsNotNull = "H001118|传入的校验取消时间不能为空";
	public static final String Order_GuaranteeAmountIsNotNull = "H001119|传入的校验担保金额不能为空";
	public static final String Order_NOTCOSTPRICE = "H001120|底价过低";
	public static final String Order_ContatMobileRequired = "H001121|联系人手机号错误";
	public static final String Order_CanNotFindRatePlan = "H001122|获取产品信息失败，获取不到RatePlan";
	public static final String Order_CheckInventory = "H001123|获取库存失败，或者房量不够";
	public static final String Order_CheckRate = "H001124|获取价格失败，或者价格不可用";
	public static final String ORDER_FAILBECAUSEOFSEARCH="H001125|搜索接口异常造成成单失败";
	public static final String Order_CustomperPrice_InvoiceMode="H001126|结算价产品需要给酒店开发票，CustomerPrice必填，且不能高于总卖价的50%";
	public static final String Common_MoreMaxLength="H001129|超过最大长度：";
	public static final String Common_BetweenRange="H001130|不许在区间内";
	public static final String NumberIds_FormatOrNumError="H001131|字符串格式错误且个数不能大于10";
	public static final String Concat_MobileNum_Check="H001132|手机号验证失败";
	public static final String CHECK_BALANCE_EXCEPTION="H001133|校验价格接口异常:{0}";
	public static final String PERMISSIONS_LIMIT="H001134|预付或现付虚拟卡权限限制，请开通权限";
	public static final String Order_RoomNeedCustomers="H001135|请确保每个房间都有客人信息";
	public static final String Order_InvoiceITINUnAvailable="H001136|发票识别号非数字字母组合或者长度不满足15、18、20位";
	
	
	public static final String Order_TotalPriceMoreThanMember="H001137-0|TotalPrice不能高于要求总卖家的{0}%";
	public static final String ORDER_TotalPriceMoreThanCost="H001037-1|TotalPrice不能高于要求总底价的{0}%";
	// endregion

	// region 搜索 H002001
	public static final String Search_ArrivalDateRangeInvalid = "H002001|入住日期范围无效";
	public static final String Search_DepartureDateRangeInvalid = "H002002|离店日期范围无效";
	public static final String Search_PriceRangeInvalid = "H002003|价格范围无效";
	public static final String Search_HasNoPrepayAuth = "H002004|没有搜索预付产品权限";
	// endregion

	// region 增量 H003001
	public static final String Incr_LastIdRequired = "H003001|最后的更新ID必须填写";
	public static final String Incr_LastTimeRequired = "H003002|最后的更新时间必须填写";
	public static final String Incr_IncrTypeRequired = "H003003|增量类型必须填写";
	public static final String Incr_Exception = "H003004|{0},error = {1}";
	// endregion

	// region 数据 H004001
	public static final String Data_NoPrepayProducts = "H004001|无预付产品权限";
	public static final String Data_NoCurrencyRate="H004002|没有当前汇率信息";
	public static final String Data_CurrencyCodeRequired="H004003|货币代码无效";
	// endregion

	// region 其他 H005001
	public static final String Other_HotelIdRequired = "H005001|请求hotelids不能为空";
	public static final String Other_HotelIdTooMany = "H005002|请求hotelid最多支持50个";
	public static final String Other_MappingDataRequired = "H005003|the mapping data is empty";
	public static final String Other_MappingDataParseError = "H005004|the mapping data cann't be parsed by JSON.";
	public static final String Other_MappingDataException = "H005005|{0}";
	
	//支付错误吗
	public static final String Payment_AmountBalanceNotEnough="支付失败，账户余额不足";
	
	//region 信用卡 H006001
	public static final String CreditCard_DESTimeStampExpired="H006001|DES加密过期－{0}";
	public static final String CreditCard_DESRuleError="H006002|DES加密规则错误-{0}";
	public static final String CreditCard_DES2TimeStampExpried="H006003|DES二级加密过期-{0}";
	public static final String CreditCard_DES2RuleError="H006004|DES二级加密规则错误-{0}";
	public static final String CreditCard_DES2KeyError="H006005|DES二级加密KEY错误-{0}";
	public static final String CreditCard_DecryptError="H006006|信用卡解密失败！";
	// endregion

	public static final String Unknown = "H099999999|未知错误";
	public static final String Parameter_SerializationError = "H000996|请求参数错误,请检查";
	
}
