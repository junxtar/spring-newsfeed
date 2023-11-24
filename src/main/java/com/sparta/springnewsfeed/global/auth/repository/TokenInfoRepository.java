package com.sparta.springnewsfeed.global.auth.repository;

import com.sparta.springnewsfeed.global.auth.entity.TokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenInfoRepository extends JpaRepository<TokenInfo, String> {

}
