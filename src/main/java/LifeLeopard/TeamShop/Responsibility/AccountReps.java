package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public interface AccountReps extends JpaRepository<Accounts,Integer> {
    Boolean existsByUsername(String Username);
    Accounts findByUsername(String Username);

    Accounts findByVerificationCode(String VerificationCode);
    Accounts findByResetPassCode(String ResetPassCode);
    boolean existsByResetPassCode(String ResetPassCode);

}
