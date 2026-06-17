package com.API.TEST.Bajaj_Finserv_RupeshBorse.Service;

import com.API.TEST.Bajaj_Finserv_RupeshBorse.dto.RequestDto;
import com.API.TEST.Bajaj_Finserv_RupeshBorse.dto.ResponseDto;

public interface BfhlService {

    ResponseDto process(RequestDto request, String requestId);

}