package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepos extends JpaRepository<Customers,Integer> {
    Customers findByAccountId(int AccountId);
    Boolean existsByEmail(String Email);
    Customers findByEmail(String Email);

}
