package com.tys.legalbases.infrastructure.external.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosGovCoNormDTO {

    private String titulo;
    private String descripcion;
    private String tipo;
    @JsonProperty("fecha")
    private String fechaPublicacion; // Will need to be parsed to OffsetDateTime
    @JsonProperty("url")
    private String urlFuente;
}
