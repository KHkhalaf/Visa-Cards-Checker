package ecommerce.visacardserviceChecker1.repository;

import ecommerce.visacardserviceChecker1.model.visacard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface visacardRepository extends JpaRepository<visacard,Long> {
}
