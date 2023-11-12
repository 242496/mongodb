package model;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@Setter
@NoArgsConstructor
public class Client extends AbstractEntity {
    @BsonProperty("username")
    private String Username;
    @BsonProperty("type")
    private ClientType Type;
    @BsonProperty("activerents")
    private Integer ActiveRents;

    @BsonCreator
    public Client(@BsonProperty("_id") UniqueId entityId,
                  @BsonProperty("username") String Username,
                  @BsonProperty("type") ClientType Type,
                  @BsonProperty("activerents") Integer ActiveRents) {
        super(entityId);
        this.Username = Username;
        this.Type = Type;
        this.ActiveRents = getActiveRents();
    }

    public Client(String Username, ClientType Type) {
        super(new UniqueId(UUID.randomUUID()));
        this.Username = Username;
        this.Type = Type;
        this.ActiveRents = getActiveRents();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("username='").append(Username).append('\'');
        sb.append(", type='").append(Type).append('\'');
        sb.append(", activerents='").append(ActiveRents);
        sb.append('}');
        return sb.toString();
    }

    public double applyDiscount(double price) {
        return this.Type.applyClientDiscount(price);
    }
}
