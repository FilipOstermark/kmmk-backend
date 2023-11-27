package music.kmmk.backend.api.mapper;

import java.util.List;

// TODO Consider MapStruct
public interface ModelDtoMapper<Model, Dto> {

    Dto modelToDto(Model model);

    Model dtoToModel(Dto dto);

    default List<Model> dtosToModels(List<Dto> dtos) {
        return dtos.stream().map(this::dtoToModel).toList();
    }

    default List<Dto> modelsToDtos(List<Model> models) {
        return models.stream().map(this::modelToDto).toList();
    }
}
