package com.sample.mainpay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RefundInquiryRequestDto {
    @NotBlank(message = "가맹점 번호를 입력하세요.")
    @Size(max = 6)
    private String merchantNo; // 섹타나인에서 부여한 가맹점 번호 (상점 아이디)

    @NotBlank(message = "원거래번호를 입력하세요.")
    @Size(max = 40)
    private String refNo; // 원거래번호 (환불 요청 API 결과 수신시 보관한 거래번호)

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