package ubb.postuniv.riddingaddict.model.pojo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ubb.postuniv.riddingaddict.model.enums.OrderType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class BigOrder extends Order {

    @Enumerated(EnumType.STRING)
    private OrderType orderType = OrderType.M;

    @Transient
    private double discount;

}
