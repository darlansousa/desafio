package com.picpaysimplifiedproject.picpaysimplifiedproject.client;


import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.client.AuthorizerResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${spring.client.urls.external-authorizer}", name = "authorizer")
public interface ExternalAuthorizerClient {

    @GetMapping("8fafdd68-a090-496f-8c9a-3442cf30dae6")
    ResponseEntity<AuthorizerResponseDTO> getAuthorizationStatus();

}
