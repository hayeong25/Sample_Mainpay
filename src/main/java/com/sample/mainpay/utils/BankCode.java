package com.sample.mainpay.utils;

import lombok.Getter;

@Getter
public enum BankCode {
    IBK("03"), KB("04"), NH("11"), WOORI("20"), SHINHAN("26"), BUSAN("32"), GWANGJU("34"), POST("71"), HANA("81");

    private final String code;
    BankCode(String code) {
        this.code = code;
    }
}