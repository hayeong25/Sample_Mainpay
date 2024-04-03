package com.sample.mainpay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RefundRequestDto {
    @NotBlank(message = "가맹점 번호를 입력하세요.")
    @Size(max = 6)
    private String merchantNo; // 섹타나인에서 부여한 가맹점 번호 (상점 아이디)

    @NotBlank(message = "주문번호를 입력하세요.")
    @Size(max = 20)
    private String orderNo; // 가맹점 주문번호 (가맹점에서 생성한 중복되지 않는 번호)

    @NotBlank(message = "지불수단을 입력하세요.")
    @Size(max = 5)
    private String paymentMethod; // 환불대상 원거래 지불수단 (CARD: 신용카드 | VACCT: 가상계좌 | ACCT: 계좌이체 | HPP: 휴대폰소액)

    @NotBlank(message = "원거래번호를 입력하세요.")
    @Size(max = 40)
    private String refNo; // 환불대상 원거래 번호

    @NotBlank(message = "원거래일자를 입력하세요.")
    @Size(max = 6)
    private String tranDate; // 환불대상 원거래 일자(YYMMDD)

    @NotBlank(message = "환불 계좌 은행 코드를 입력하세요.")
    @Size(max = 2)
    private String bankCode; // 환불계좌 은행사코드

    @NotBlank(message = "환불 계좌 번호")
    @Size(max = 16)
    private String accountNumber; // 환불계좌번호

    @NotBlank(message = "환불 계좌 예금주")
    @Size(max = 20)
    private String accountHolder; // 환불계좌주명

    @NotBlank(message = "예금주의 생년월일 혹은 사업자 등록번호를 입력하세요")
    @Size(max = 10)
    private String regNo;

    @Min(1)
    private int amount; // 환불 금액

    @NotBlank(message = "user id를 입력하세요.")
    @Size(max = 20)
    private String userId; // 요청 user id

    @NotBlank(message = "환불 요청 시각을 입력하세요.")
    @Size(max = 18)
    private String requestDateTime; // 가맹점 시스템 시각 (yyMMddHHmmssSSS)

    @NotBlank(message = "signature 값을 입력하세요.")
    @Size(max = 64)
    private String signature; // 결제 위변조 방지를 위한 파라미터 서명 값
}