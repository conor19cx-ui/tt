package org.example.week08.sms;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 关键：你的ApiResult在sms包下，直接导入
import org.example.week08.sms.ApiResult;
// 导入dto下的所有请求/响应类
import org.example.week08.sms.dto.SendCodeRequest;
import org.example.week08.sms.dto.SendCodeResponse;
import org.example.week08.sms.dto.ValidateCodeRequest;
import org.example.week08.sms.dto.ValidateCodeView;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsVerifyCodeController {

    private final SmsVerifyCodeService smsVerifyCodeService;

    /**
     * 发送验证码接口
     */
    @PostMapping("/verify-codes")
    public ApiResult<SendCodeResponse> send(
            @RequestBody @Valid SendCodeRequest request) {
        SendCodeResponse response = smsVerifyCodeService.sendCode(request.phone());
        return ApiResult.success(response);
    }

    /**
     * 校验验证码接口
     */
    @PostMapping("/verify-codes/validate")
    public ResponseEntity<ApiResult<ValidateCodeView>> validate(
            @RequestBody @Valid ValidateCodeRequest request) {
        boolean isValid = smsVerifyCodeService.validateCode(request.phone(), request.code());
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(ApiResult.error(422, "验证码错误或已过期"));
        }
        return ResponseEntity.ok(ApiResult.success(new ValidateCodeView(true)));
    }
}