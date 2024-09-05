package pet.project.organizationservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NullSafeUtils {

    public static <T, R> R applyIfNotNull(T object, Function<T, R> function, R defaultValue) {
        if (Objects.isNull(object)) {
            return defaultValue;
        }
        return function.apply(object);
    }

    public static <T, R> R applyIfNotNull(T object, Function<T, R> function) {
        return applyIfNotNull(object, function, null);
    }

}
