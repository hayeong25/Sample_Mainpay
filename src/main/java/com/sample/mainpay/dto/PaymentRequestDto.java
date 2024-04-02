package com.sample.mainpay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
    @NotBlank(message = "가맹점 번호를 입력하세요.")
    @Size(max = 6)
    private String merchantNo; // 섹타나인에서 부여한 가맹점 번호 (상점 아이디)

    @NotBlank(message = "주문번호를 입력하세요.")
    @Size(max = 20)
    private String orderNo; // 가맹점 주문번호 (가맹점에서 생성한 중복되지 않는 번호)

    @NotBlank(message = "지불수단을 입력하세요.")
    @Size(max = 5)
    private String paymentMethod; // 지불수단 (CARD 고정값)

    @NotBlank(message = "카드번호를 입력하세요.")
    @Size(max = 16)
    private String cardNumber; // 카드번호

    @NotBlank(message = "카드 유효기간을 입력하세요.")
    @Size(max = 4)
    private String expiredPeriod; // 카드유효기간 (YYMM)

    @Min(1)
    private int amount; // 결제금액 (공급가+부가세)

    @Min(0)
    @Max(24)
    private int installment; // 할부개월 (0 ~ 24)

    @NotBlank(message = "상품명을 입력하세요.")
    @Size(max = 30)
    @Pattern(regexp = "/[{}\\[\\]/?.,;:|)*~`!^\\-_+<>@#$%&\\\\=('\"]/g", message = "상품명에는 특수문자를 사용할 수 없습니다.")
    private String productName; // 상품명

    @NotBlank(message = "결제 요청 시각을 입력하세요.")
    private LocalDateTime requestDateTime; // 가맹점 시스템 시각 (yyMMddHHmmssSSS)

    @NotBlank(message = "signature 값을 입력하세요.")
    @Size(max = 64)
    private String signature; // 결제 위변조 방지를 위한 파라미터 서명 값

    @NotBlank(message = "인증방식을 선택하세요.")
    @Size(max = 1)
    private String keyinAuthType; // 키인 인가 구분 (K:비인증 | O:구인증)

    @Min(0)
    @Max(1)
    private int authType; // 구인증용 인증타입 (0: 생년월일 | 1: 사업자번호)

    @NotBlank(message = "생년월일 혹은 사업자 번호를 입력하세요.")
    @Size(max = 10)
    private String regNo; // 구인증용 아이디 (생년월일(YYMMDD) | 사업자번호)

    @NotBlank(message = "카드 비밀번호 앞 2자리를 입력하세요.")
    @Size(max = 2)
    private String password; // 구인증용 카드 비밀번호 앞 2자리

    @Size(max = 30)
    private String customerName; // 구매자명

    @Size(max = 12)
    private String customerTelNo; // 구매자 연락처

    @Size(max = 50)
    private String customerEmail; // 구매자 이메일
    
    @Size(max = 16)
    private String retailerCode; // 거래처 코드
}