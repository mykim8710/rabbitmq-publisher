package rabbitmq.ex.publisher.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RequestOrderDto {
    private String itemName;
    private int quantity;
}
