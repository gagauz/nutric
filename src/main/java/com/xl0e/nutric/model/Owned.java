package com.xl0e.nutric.model;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public interface Owned {

    Owned getOwner();

    default boolean isOwnedBy(final Object object) {
        if (Objects.equals(object, this)) {
            return true;
        }
        final Owned owner = getOwner();

        if (Objects.equals(object, owner)) {
            return true;
        }
        return null != owner && !Objects.equals(owner, this) && getOwner().isOwnedBy(object);
    }

    default <T extends Owned> Optional<T> findOwner(final Predicate<Owned> predicate) {
        final Owned owner = getOwner();

        if (predicate.test(owner)) {
            return (Optional<T>) Optional.ofNullable(owner);
        }
        if (null != owner && !Objects.equals(owner, this)) {
            return owner.findOwner(predicate);
        }
        return Optional.empty();
    }
}
