package ecommerce.visacardserviceChecker.repository;

import ecommerce.visacardserviceChecker.model.visacard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface visacardRepository extends JpaRepository<visacard,Long> {
}
