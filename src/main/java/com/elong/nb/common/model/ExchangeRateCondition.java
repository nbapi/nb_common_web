package com.elong.nb.common.model;

import com.google.gson.annotations.SerializedName;

public class ExchangeRateCondition {

	@SerializedName(value = "CurrencyId", alternate = "CurrencyID")
	private EnumCurrencyCode CurrencyId;

	public final EnumCurrencyCode getCurrencyId() {
		return CurrencyId;
	}

	public final void setCurrencyId(EnumCurrencyCode value) {
		CurrencyId = value;
	}

}
