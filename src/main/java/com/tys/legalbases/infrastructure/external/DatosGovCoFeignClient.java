package com.tys.legalbases.infrastructure.external;

import com.tys.legalbases.infrastructure.external.dtos.DatosGovCoNormDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "datosGovCoClient", url = "${external.api.datosgovco.host}")
public interface DatosGovCoFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/resource/88h2-dykw.json")
    List<DatosGovCoNormDTO> getLegalBases();
}
