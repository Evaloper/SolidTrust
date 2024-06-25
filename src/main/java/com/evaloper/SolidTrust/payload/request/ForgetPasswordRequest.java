package com.evaloper.SolidTrust.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForgetPasswordRequest {
    private String email;

    private String newPassword;

    private String oldPassword;
}
