package org.fan.cloud.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.fan.cloud.common.config.JwtConfig;
import org.fan.cloud.common.util.SpringContextUtil;
import org.fan.cloud.gateway.entity.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author fanfanlordship
 * @Description 登录校验
 * @Date 2023/9/26 16:29
 */
@Component
public class JWTAuthFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthFilter.class);

    private static final ObjectMapper om = new ObjectMapper();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();

        //跳过不需要验证的路径
        if (isSkip(url)) {
            return chain.filter(exchange);
        }

        JwtConfig jwtConfig = SpringContextUtil.getBean(JwtConfig.class);

        //从请求头中取得token
        String token = exchange.getRequest().getHeaders().getFirst(jwtConfig.getHeader());

        if (Strings.isNullOrEmpty(token)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

            ResultData resultData = new ResultData();
            resultData.setCode("401");
            resultData.setMessage("请求token信息缺失");

            byte[] bytes;
            try {
                bytes = om.writeValueAsBytes(resultData);
            } catch (JsonProcessingException e) {
                LOGGER.error(e.getMessage(), e);
                bytes = new byte[]{};
            }

            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(buffer));
        }

        //请求中的token是否有效
        boolean verifyResult = verifyToken(token);
        if (!verifyResult) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

            ResultData resultData = new ResultData();
            resultData.setCode("401");
            resultData.setMessage("请求token信息缺失");

            byte[] bytes;
            try {
                bytes = om.writeValueAsBytes(resultData);
            } catch (JsonProcessingException e) {
                LOGGER.error(e.getMessage(), e);
                bytes = new byte[]{};
            }

            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(buffer));

        }

        //如果各种判断都通过，执行chain上的其他业务逻辑
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private boolean isSkip(String url) {
        JwtConfig jwtConfig = SpringContextUtil.getBean(JwtConfig.class);
        return jwtConfig.getIgnoreUrls().contains(url);
    }

    public boolean verifyToken(String token) {
        try {
            JwtConfig jwtConfig = SpringContextUtil.getBean(JwtConfig.class);
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecretKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 如果校验有问题会抛出异常
            verifier.verify(token);
            return true;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return false;
    }
}
