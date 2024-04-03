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
public class CardNotiRequestDto {
    @Min(0) @Max(2)
    private int cmd; // 요청방식 (0: 승인 | 1: 취소 | 2: 부분취소)

    @NotBlank(message = "지불수단 확인 불가")
    @Size(max = 10)
    private String paymethod; // 지불수단 (CARD: 신용카드 | CASH: 현금영수증)

    @NotBlank(message = "결제 타입 확인 불가")
    @Size(max = 2)
    private String payType; // 결제 타입

    @NotBlank(message = "요청 구분 확인 불가")
    @Size(max = 1)
    private String requestFlag; // 요청구분 (C:단말 | A:온라인거래 | K:수기결제/간편결제)

    @NotBlank(message = "가맹점 번호 확인 불가")
    @Size(max = 6)
    private String mbrNo; // 섹타나인에서 부여한 가맹점 번호 (상점 아이디)

    @NotBlank(message = "거래 번호 확인 불가")
    @Size(max = 20)
    private String refNo; // PG 거래번호

    @NotBlank(message = "거래일자 확인 불가")
    @Size(max = 6)
    private String tranDate; // 거래일자

    @NotBlank(message = "거래시각 확인 불가")
    @Size(max = 6)
    private String tranTime; // 거래시각

    @NotBlank(message = "가맹점 주문번호 확인 불가")
    @Size(max = 20)
    private String mbrRefNo; // 가맹점 주문번호 (가맹점에서 생성한 중복되지 않는 번호)

    @Size(max = 12)
    private String orgRefNo; // 원거래번호
    
    @Size(max = 6)
    private String orgTranDate; // 원거래일자

    @NotBlank(message = "VAN CAT ID 확인 불가")
    @Size(max = 10)
    private String vanCatId; // VAN_CAT_ID

    @NotBlank(message = "카드사 가맹점 번호 확인 불가")
    @Size(max = 15)
    private String cardMerchNo; // 카드사 가맹점 번호

    @NotBlank(message = "승인번호 확인 불가")
    @Size(max = 20)
    private String applNo; // 승인번호 (신용카드: 8자리, 현금영수증 : 9자리)

    @NotBlank(message = "신용카드 발급사 코드 확인 불가")
    @Size(max = 2)
    private String issueCompanyNo; // 신용카드 발급사코드

    @NotBlank(message = "신용카드 매입사 코드 확인 불가")
    @Size(max = 2)
    private String acqCompanyNo; // 신용카드 매입사코드

    @NotBlank(message = "카드번호 확인 불가")
    @Size(max = 20)
    private String cardNo; // 마스킹 처리된 카드번호

    @NotBlank(message = "할부개월 수, 현금영수증 구분 확인 불가")
    @Size(max = 2)
    private String installNo; // 신용카드: 할부개월수, 현금영수증(01:개인[소득공제], 02:법인)

    @NotBlank(message = "상품명 확인 불가")
    @Size(max = 40)
    private String goodsName; // 상품명

    @Min(1)
    private int amount; // 결제금액

    @Size(max = 30)
    private String customerName; // 구매자명

    @Size(max = 12)
    private String customerTelNo; // 구매자 연락처

    @Size(max = 50)
    private String customerEmail; // 구매자 이메일

    @Size(max = 30)
    private String sid; // 직원 ID

    @Size(max = 16)
    private String retailerCode; // 거래처 코드
}