package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RolesRepos extends JpaRepository<Roles,Integer> {
//    Boolean existsByRoleName(String RoleName);
    Roles findByRoleName(String RoleName);
}
