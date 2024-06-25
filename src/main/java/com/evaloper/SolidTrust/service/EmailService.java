package com.evaloper.SolidTrust.service;

import com.evaloper.SolidTrust.payload.request.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);

    void sendEmailWithAttachment(EmailDetails emailDetails);
}
