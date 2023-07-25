package com.user.Service.Repositary;

import com.user.Service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User,String> {

}
