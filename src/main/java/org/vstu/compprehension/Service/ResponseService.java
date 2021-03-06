package org.vstu.compprehension.Service;

import org.vstu.compprehension.models.repository.ResponseRepository;
import org.vstu.compprehension.models.entities.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    private ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }
    
    public void saveResponse(ResponseEntity response) {
        
        responseRepository.save(response);
    }
}
