package music.kmmk.backend.data.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * A generic mapper between a database entity type and a domain model type.
 * @param <Entity> The entity type
 * @param <Model> The model type
 */
// TODO Consider MapStruct
public interface EntityModelMapper<Entity, Model> {

    Entity modelToEntity(Model model);

    Model entityToModel(Entity entity);

    default List<Entity> modelsToEntities(Collection<Model> models) {
        return models.stream().map(this::modelToEntity).toList();
    }

    default List<Model> entitiesToModels(Collection<Entity> entities) {
        return entities.stream().map(this::entityToModel).toList();
    }

    default List<Model> entitiesToModels(Iterable<Entity> entities) {
        return StreamSupport.stream(entities.spliterator(), false).map(this::entityToModel).toList();
    }

}
