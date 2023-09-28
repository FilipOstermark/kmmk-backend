package music.kmmk.backend.common.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * A generic mapper between a database entity type and a DTO type. Implements some collection/stream operations by
 * default.
 * @param <Entity> The entity type
 * @param <Dto> The DTO type
 */
public interface EntityDtoMapper<Entity, Dto> {

    Entity toEntity(Dto dto);

    Dto toDto(Entity entity);

    default List<Entity> toEntityList(Collection<Dto> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }

    default List<Dto> toDtoList(Collection<Entity> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    default List<Dto> toDtoList(Iterable<Entity> entities) {
        return StreamSupport.stream(entities.spliterator(), false).map(this::toDto).toList();
    }

}
