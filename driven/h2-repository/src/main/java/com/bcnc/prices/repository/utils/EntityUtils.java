package com.bcnc.prices.repository.utils;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.hibernate.proxy.HibernateProxy;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

@UtilityClass
public class EntityUtils {
    @SafeVarargs
    public <E, ID> boolean equals(
        @NonNull E a,
        @Nullable Object b,
        @NonNull Function<E, ID> idGetter,
        @NonNull Function<E, ?>... otherGetters
        ) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(idGetter);
        Objects.requireNonNull(otherGetters);

        if (a == b) return true;
        if (b == null) return false;

        if (getEffectiveClass(a) != getEffectiveClass(b)) return false;

        final E that = (E) b;

        if (otherGetters.length == 0) {
            final ID id = idGetter.apply(a);
            return id != null && Objects.equals(id, idGetter.apply(that));
        }

        return Stream.concat(
            Stream.of(idGetter),
            Arrays.stream(otherGetters)
        ).allMatch(getter -> {
            final var value = getter.apply(a);
            return value != null && Objects.equals(value, getter.apply(that));
        });
    }

    public int hashCode(@NonNull Object value) {
        Objects.requireNonNull(value);

        return getEffectiveClass(value).hashCode();
    }

    @NonNull
    private Class<?> getEffectiveClass(@NonNull Object object) {
        return object instanceof HibernateProxy proxy
            ? proxy.getHibernateLazyInitializer().getPersistentClass()
            : object.getClass();
    }

}
