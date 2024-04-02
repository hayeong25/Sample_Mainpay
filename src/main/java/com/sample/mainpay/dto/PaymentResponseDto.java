package com.sample.mainpay.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentResponseDto {
    private String resultCode; // 응답코드 '200' 이면 성공, 이외는 거절
    private String resultMessage; // 응답 메시지
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private String mbrNo; // 가맹점 번호
        private String mbrRefNo; // 가맹점 주문번호
        private String refNo; // 거래번호 (거래 취소시 필요)
        private String tranDate; // 거래일자 (거래 취소시 필요)
        private String payType; // 결제타입 (거래 취소시 필요)
        private LocalDateTime tranTime; // 거래시각
        private int amount; // 결제금액
        private String applNo; // 승인번호
        private String issueCompanyNo; // 카드 발급사 코드
        private String issueCompanyName; // 카드 발급사명
        private String issueCardName; // 발급 카드명
        private String acqCompanyNo; // 카드 매입사 코드
        private String acqCompanyName; // 카드 매입사명
    }
}