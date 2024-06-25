package com.evaloper.SolidTrust.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AuthenticationUtils {
    public static String applicationUrl(HttpServletRequest request){
        return "http://"+request.getServerName()+":"
                +request.getServerPort()+
                request.getContextPath();

    }

}

