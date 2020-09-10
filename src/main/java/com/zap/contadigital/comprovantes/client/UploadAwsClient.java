package com.zap.contadigital.comprovantes.client;

import com.zap.contadigital.comprovantes.dto.request.UploadClientRequest;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name ="uploadAwsClient", url = "http://192.168.0.100:3000/v1/upload")
public interface UploadAwsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/upload")
    @Headers({"Content-Type:application/json"})
    String getUrlFromAws(@RequestBody UploadClientRequest request);
}
