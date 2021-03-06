package be.feelio.mollie.data.order;

import be.feelio.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineLinks {

    private Link self;

    @Builder.Default
    private Optional<Link> checkout = Optional.empty();

    private Link documentation;
}
