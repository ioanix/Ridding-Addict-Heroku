package ubb.postuniv.riddingaddict.model.pojo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ubb.postuniv.riddingaddict.model.enums.OrderType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class SmallOrder extends Order {

    @Enumerated(EnumType.STRING)
    private OrderType orderType = OrderType.S;
}
