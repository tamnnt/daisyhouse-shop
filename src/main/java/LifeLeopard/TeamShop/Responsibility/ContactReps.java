package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactReps extends JpaRepository<Contact,Integer> {
    Contact findByStatusIs(int Status);

}
