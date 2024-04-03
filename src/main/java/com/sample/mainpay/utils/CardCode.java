package com.sample.mainpay.utils;

import lombok.Getter;

@Getter
public enum CardCode {
    VISA("AV"), MASTER("AM"), JCB("AJ"), NAVER("NP"), APPLE("AP"), BC("01"), SHINHAN("02"), SAMSUNG("03"), HYUNDAI("04"),
    LOTTE("05"), KB("07"), HANA("08"), GLOBAL("09"), SH("11"), NH("12"), HANMI("13"), CITI("15"), WOORI("20"), JEJU("22"),
    GWANGJU("23"), JB("24"), SSG("30"), KAKAO("31"), PURMEE("32"), ONNURI("34"), KONA("35"), JDREAM("36"), TOSS("39");

    private final String code;
    CardCode(String code) {
        this.code = code;
    }
}