package com.sample.mainpay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VirtualAccountCancelRequestDto {
    @NotBlank(message = "가맹점 번호를 입력하세요.")
    @Size(max = 6)
    private String merchantNo; // 섹타나인에서 부여한 가맹점 번호 (상점 아이디)

    @NotBlank(message = "주문번호를 입력하세요.")
    @Size(max = 20)
    private String orderNo; // 가맹점 주문번호 (가맹점에서 생성한 중복되지 않는 번호)

    @Min(1)
    private int amount; // 금액
    
    @NotBlank(message = "승인일자를 입력하세요.")
    @Size(max = 6)
    private String approvalDate; // 원거래 승인일자 (승인 응답시 보관한 승인일자)

    @NotBlank(message = "발급 받은 가상 계좌 번호를 입력하세요.")
    @Size(max = 20)
    private String accountNumber; // 가상 계좌 번호

    @NotBlank(message = "결제 요청 시각을 입력하세요.")
    private LocalDateTime requestDateTime; // 가맹점 시스템 시각 (yyMMddHHmmssSSS)

    @NotBlank(message = "signature 값을 입력하세요.")
    @Size(max = 64)
    private String signature; // 결제 위변조 방지를 위한 파라미터 서명 값
}