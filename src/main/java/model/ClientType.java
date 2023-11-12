package model;


import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@Setter
@BsonDiscriminator(key = "_clazz")
public abstract class ClientType extends AbstractEntity {

    @BsonProperty("maxrents")
    protected int MaxRents;
    @BsonProperty("clientdiscount")
    protected double ClientDiscount;
    @BsonProperty("maxdays")
    protected int MaxDays;

    @BsonCreator
    public ClientType(@BsonProperty("_id") UniqueId entityId,
                      @BsonProperty("maxrents") int MaxRents,
                      @BsonProperty("clientdiscount") double ClientDiscount,
                      @BsonProperty("maxdays") int MaxDays) {
        super(entityId);
        this.MaxRents = MaxRents;
        this.ClientDiscount = ClientDiscount;
        this.MaxDays = MaxDays;
    }

    public ClientType() {
        super(new UniqueId(UUID.randomUUID()));
        this.MaxRents = getMaxRents();
        this.ClientDiscount = getClientDiscount();
        this.MaxDays = getMaxDays();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientType{");
        sb.append("maxrents='").append(MaxRents).append('\'');
        sb.append(", clientdiscount='").append(ClientDiscount).append('\'');
        sb.append(", maxdays='").append(MaxDays);
        sb.append('}');
        return sb.toString();
    }

    public int getMaxRents() {
        return MaxRents;
    }

    public double getClientDiscount() {
        return ClientDiscount;
    }


    public void setClientDiscount(double clientDiscount) {
        ClientDiscount = clientDiscount;
    }

    public double applyClientDiscount(double price) {
        return price;
    }

    public int getMaxDays() {return MaxDays; }

}
