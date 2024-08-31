package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Verify JWT
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(@Nullable HttpServletRequest request,
                             @Nullable HttpServletResponse response,
                             @Nullable Object handler) throws Exception {
        // Judge the intercepting role
        // if it's controller, continue to verify
        // let it pass otherwise
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //1、Get token
        assert request != null;
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、examine jwt
        try {
            log.info("Examined jwt:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("Current employee id：{}", empId);

            // Store current user id to ThreadLocal
            BaseContext.setCurrentId(empId);
            //3、pass
            return true;
        } catch (Exception ex) {
            //4、not verified
            assert response != null;
            response.setStatus(401);
            return false;
        }
    }
}
