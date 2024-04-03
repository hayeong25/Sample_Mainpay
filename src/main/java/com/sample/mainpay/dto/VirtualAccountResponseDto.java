package com.sample.mainpay.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VirtualAccountResponseDto {
    private String resultCode; // 응답코드 '200' 이면 성공, 이외는 거절
    private String resultMessage; // 응답 메시지
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private String mbrNo; // 가맹점 번호
        private String mbrRefNo; // 가맹점 주문번호
        private String refNo; // 거래번호
        private String tranDate; // 거래일자
        private LocalDateTime tranTime; // 거래시각
        private int amount; // 결제금액
        private String accountNo; // 가상 계좌 번호
        private String bankCode; // 은행 코드
        private String accountCloseDate; // 입금 완료 기한
    }
}