package com.sample.mainpay.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@ToString
public class CancelRequestDto {
    @NotBlank(message = "가맹점 번호를 입력하세요.")
    @Size(max = 6)
    private String merchantNo; // 섹타나인에서 부여한 가맹점 번호 (상점 아이디)

    @NotBlank(message = "주문번호를 입력하세요.")
    @Size(max = 20)
    private String orderNo; // 가맹점 주문번호 (가맹점에서 생성한 중복되지 않는 번호)

    @NotBlank(message = "승인 거래번호를 입력하세요.")
    @Size(max = 12)
    private String refNo; // 원거래번호 (승인 응답시 보관한 거래번호)

    @NotBlank(message = "승인일자를 입력하세요.")
    @Size(max = 6)
    private String approvalDate; // 원거래 승인일자 (승인 응답시 보관한 승인일자)

    @NotNull(message = "결제타입을 입력하세요.")
    @Size(max = 2)
    private String payType; // 원거래 결제타입 (승인 응답 시 보관한 결제타입) 단, 해당 지불수단이 payType을 제공하지 않을 경우는 공백

    @NotBlank(message = "지불수단을 입력하세요.")
    @Size(max = 5)
    private String paymentMethod; // 지불수단 (CARD:신용카드 | HPP: 휴대폰소액 | ACCT: 계좌이체)

    @Min(1)
    private int amount; // 원거래 금액

    @NotBlank(message = "결제 요청 시각을 입력하세요.")
    private String requestDateTime; // 가맹점 시스템 시각 (yyMMddHHmmssSSS)

    @NotBlank(message = "signature 값을 입력하세요.")
    @Size(max = 64)
    private String signature; // 결제 위변조 방지를 위한 파라미터 서명 값
}