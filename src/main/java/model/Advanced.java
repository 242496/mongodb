package model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@Getter
@BsonDiscriminator(key = "_clazz",value = "advanced")
public class Advanced extends ClientType {

    @BsonCreator
    public Advanced() { }

    @JsonCreator
    public Advanced(@JsonProperty("_id") UniqueId entityId,
                    @JsonProperty("maxrents") int MaxRents,
                    @JsonProperty("clientdiscount") double ClientDiscount,
                    @JsonProperty("maxdays") int MaxDays) {
        super(entityId, MaxRents, ClientDiscount, MaxDays);
    }

    @Override
    public double applyClientDiscount(double price) {
        ClientDiscount = price * 0.7;
        return ClientDiscount;
    }

    @Override
    public int getMaxRents() {
        return 5;
    }

    @Override
    public int getMaxDays() { return 90; }

}
