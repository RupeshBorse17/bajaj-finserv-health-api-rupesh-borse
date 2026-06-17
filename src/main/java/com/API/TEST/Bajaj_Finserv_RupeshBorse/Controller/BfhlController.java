package com.API.TEST.Bajaj_Finserv_RupeshBorse.Controller;

import com.API.TEST.Bajaj_Finserv_RupeshBorse.Service.BfhlService;
import com.API.TEST.Bajaj_Finserv_RupeshBorse.dto.RequestDto;
import com.API.TEST.Bajaj_Finserv_RupeshBorse.dto.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService service;

    public BfhlController(BfhlService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseDto process(
            @Valid @RequestBody RequestDto request,
            @RequestHeader("X-Request-Id") String requestId) {

        return service.process(request, requestId);
    }
}