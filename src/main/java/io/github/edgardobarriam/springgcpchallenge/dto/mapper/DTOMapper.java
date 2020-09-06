package io.github.edgardobarriam.springgcpchallenge.dto.mapper;

public interface DTOMapper<S,T> {
  T toDTO(S model);
  S toModel(T dto);
}
