package ltd.user.cloud.newbee.config.handler;

import ltd.common.cloud.newbee.enums.ServiceResultEnum;
import ltd.common.cloud.newbee.exception.NewBeeMallException;
import ltd.common.cloud.newbee.pojo.MallUserToken;
import ltd.user.cloud.newbee.config.annotation.TokenToMallUser;
import ltd.user.cloud.newbee.dao.MallUserMapper;
import ltd.user.cloud.newbee.entity.MallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class TokenToMallUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MallUserMapper mallUserMapper;

    public TokenToMallUserMethodArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToMallUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToMallUser.class) instanceof TokenToMallUser) {
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                ValueOperations<String, MallUserToken> opsForMallUserToken = redisTemplate.opsForValue();
                MallUserToken mallUserToken = opsForMallUserToken.get(token);
                if (mallUserToken == null ) {
                    NewBeeMallException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }
                MallUser mallUser = mallUserMapper.selectByPrimaryKey(mallUserToken.getUserId());
                if (mallUser == null) {
                    NewBeeMallException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }
                if (mallUser.getLockedFlag().intValue() == 1) {
                    NewBeeMallException.fail(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
                }
                return mallUserToken;
            } else {
                NewBeeMallException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }
}
