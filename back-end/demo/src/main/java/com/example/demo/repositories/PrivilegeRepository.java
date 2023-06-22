package com.example.demo.repositories;

import com.example.demo.models.Privilege;
import com.example.demo.models.PrivilegeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findPrivilegeByName(PrivilegeName name);
}
