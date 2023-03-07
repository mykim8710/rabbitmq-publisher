package rabbitmq.ex.publisher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rabbitmq.ex.publisher.dto.RequestOrderDto;
import rabbitmq.ex.publisher.vo.OrderMqVo;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private static final String EXCHANGE_NAME = "order.exchange";
    private static final String ROUTING_KEY = "order.oingdaddy.#";
    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;


    @PostMapping("/api/v1/order")
    public ResponseEntity<String> orderApi(@RequestBody RequestOrderDto requestOrderDto) throws JsonProcessingException {
        log.info("[POST] /api/v1/order, {}" , requestOrderDto);

        // do Order...... Service Logic
        OrderMqVo orderMqVo = OrderMqVo.builder()
                .mail("mykim8710@gmail.com")
                .template("ORDER_SUCCESS")
                .build();
            //objectMapper.writeValueAsString(orderMqVo)
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, orderMqVo);

        return ResponseEntity
                .ok()
                .body("ORDER SUCCESS");
    }





}
