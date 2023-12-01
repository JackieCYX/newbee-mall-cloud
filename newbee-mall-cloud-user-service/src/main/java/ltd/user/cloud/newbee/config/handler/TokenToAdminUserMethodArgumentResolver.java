package ltd.user.cloud.newbee.config.handler;

import ltd.user.cloud.newbee.config.annotation.TokenToAdminUser;
import ltd.user.cloud.newbee.dao.NewBeeAdminUserTokenMapper;
import ltd.user.cloud.newbee.entity.AdminUserToken;
import ltd.common.cloud.newbee.exception.NewBeeMallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class TokenToAdminUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private NewBeeAdminUserTokenMapper newBeeAdminUserTokenMapper;

    public TokenToAdminUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToAdminUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getParameterAnnotation(TokenToAdminUser.class) instanceof TokenToAdminUser) {
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                AdminUserToken adminUserToken = newBeeAdminUserTokenMapper.selectByToken(token);
                if (adminUserToken == null) {
                    NewBeeMallException.fail("ADMIN_NOT_LOGIN_ERROR");
                } else if (adminUserToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                    NewBeeMallException.fail("ADMIN_TOKEN_EXPIRE_ERROR");
                }
                return adminUserToken;
            } else {
                NewBeeMallException.fail("ADMIN_NOT_LOGIN_ERROR");
            }
        }
        return null;
    }

}
