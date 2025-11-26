package com.josepedevs.infra.rest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
    name = "PERSONBATCH",
    configuration = FeignAsyncConfig.class
)
public interface RestBatchClient {

    @PostMapping("/run-job")
    ResponseEntity<String> runJob();

    @Async
    default void runJobAsync() {
        runJob();
    }
}
