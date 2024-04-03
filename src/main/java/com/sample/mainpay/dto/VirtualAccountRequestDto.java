package com.sample.mainpay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VirtualAccountRequestDto {
    @NotBlank(message = "가맹점 번호를 입력하세요.")
    @Size(max = 6)
    private String merchantNo; // 섹타나인에서 부여한 가맹점 번호 (상점 아이디)

    @NotBlank(message = "주문번호를 입력하세요.")
    @Size(max = 20)
    private String orderNo; // 가맹점 주문번호 (가맹점에서 생성한 중복되지 않는 번호)

    @NotBlank(message = "은행 코드를 입력하세요.")
    @Size(max = 2)
    private String bankCode; // 입금 은행 코드

    @Min(1)
    private int amount; // 결제금액 (공급가+부가세)

    private LocalDate payLimitDay; // 입금완료 기한 (YYMMDD)

    @NotBlank(message = "상품명을 입력하세요.")
    @Size(max = 30)
    @Pattern(regexp = "/[{}\\[\\]/?.,;:|)*~`!^\\-_+<>@#$%&\\\\=('\"]/g", message = "상품명에는 특수문자를 사용할 수 없습니다.")
    private String productName; // 상품명

    @Size(max = 30)
    private String customerName; // 구매자명

    @Size(max = 12)
    private String customerTelNo; // 구매자 연락처

    @Size(max = 50)
    private String customerEmail; // 구매자 이메일

    @NotBlank(message = "결제 요청 시각을 입력하세요.")
    private LocalDateTime requestDateTime; // 가맹점 시스템 시각 (yyMMddHHmmssSSS)

    @NotBlank(message = "signature 값을 입력하세요.")
    @Size(max = 64)
    private String signature; // 결제 위변조 방지를 위한 파라미터 서명 값
}