package model;

import lombok.NoArgsConstructor;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

@NoArgsConstructor
public class UniqueIdCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> aClass, CodecRegistry codecRegistry) {
        if (aClass == UniqueId.class) {
            return (Codec<T>) new UniqueIdCodec(codecRegistry);
        }
        return null;
    }
}
