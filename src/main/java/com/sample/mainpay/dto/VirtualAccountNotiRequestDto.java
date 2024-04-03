package com.sample.mainpay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VirtualAccountNotiRequestDto {
    @NotBlank(message = "V2 고정값 확인 불가")
    @Size(max = 2)
    private String TRGUBUN; // V2

    @NotBlank(message = "가맹점 번호 확인 불가")
    @Size(max = 6)
    private String STOREID; // 가맹점 번호 (mbrNo와 동일)

    @NotBlank(message = "가맹점 주문번호 확인 불가")
    @Size(max = 20)
    private String ORDNO; // 가맹점주문번호 (mbrRefNo와 동일)

    @NotBlank(message = "거래번호 확인 불가")
    @Size(max = 12)
    private String REF_NO; // 가상계좌 채번 시 전달한 거래번호 (refNo와 동일)

    @NotBlank(message = "입금일자 확인 불가")
    @Size(max = 8)
    private String TRDATE; // 입금일자

    @NotBlank(message = "입금시각 확인 불가")
    @Size(max = 6)
    private String TRTIME; // 입금시각

    @NotBlank(message = "가상계좌번호 확인 불가")
    @Size(max = 14)
    private String ACCOUNTNO; // 가상계좌번호

    @NotBlank(message = "입금자명 확인 불가")
    @Size(max = 10)
    private String RCPTNAME; // 입금자명

    @Min(1)
    private int AMT; // 입금금액

    @NotBlank(message = "은행코드 확인 불가")
    @Size(max = 3)
    private String BANK_CODE; // 은행코드

    @NotBlank(message = "응답코드 확인 불가")
    @Size(max = 4)
    private String REPLYCODE; // 응답코드("0000" 성공, 그 외 거절)
}