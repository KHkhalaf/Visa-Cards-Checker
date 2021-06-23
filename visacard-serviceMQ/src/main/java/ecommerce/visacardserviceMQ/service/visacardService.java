package ecommerce.visacardserviceMQ.service;

import ecommerce.visacardserviceMQ.model.visacard;
import ecommerce.visacardserviceMQ.repository.visacardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class visacardService {

    @Autowired
    private visacardRepository repo;

    public List<visacard> getAll(){

        return repo.findAll();
    }

    public void saveVisaCard(visacard visacard){
        repo.save(visacard);
    }
}
