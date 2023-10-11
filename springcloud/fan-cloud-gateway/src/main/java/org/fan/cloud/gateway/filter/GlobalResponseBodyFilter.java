package org.fan.cloud.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fan.cloud.gateway.entity.ResultData;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author fanfanlordship
 * @Description 统一包装响应参数
 * @Date 2023/9/22 1:59
 */
@Component
public class GlobalResponseBodyFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalResponseBodyFilter.class);

    private static final ObjectMapper om = new ObjectMapper();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

                if (getStatusCode() != null && HttpStatus.OK.equals(getStatusCode()) && body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                        DataBuffer join = dataBufferFactory.join(dataBuffers);
                        byte[] content = new byte[join.readableByteCount()];
                        join.read(content);
                        DataBufferUtils.release(join);
                        //完整得响应体
                        String responseData = new String(content, StandardCharsets.UTF_8);

                        Object obj;
                        try {
                            obj = om.readValue(responseData, Object.class);
                        } catch (JsonProcessingException e) {
                            obj = responseData;
                        }

                        ResultData success = ResultData.success(obj);

                        byte[] bytes = null;
                        try {
                            bytes = om.writeValueAsBytes(success);
                        } catch (JsonProcessingException e) {
                            LOGGER.error(e.getMessage(), e);
                        }

                        // 修改响应结果长度
                        originalResponse.getHeaders().setContentLength(bytes == null ? 0 : bytes.length);

                        return bufferFactory.wrap(bytes == null ? new byte[]{} : bytes);
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return -999;
    }
}
