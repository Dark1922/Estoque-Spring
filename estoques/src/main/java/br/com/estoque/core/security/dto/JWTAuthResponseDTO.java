package br.com.estoque.core.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JWTAuthResponseDTO {

    private String tokenAccess;
    
    private String tokenType = "Bearer";

    public JWTAuthResponseDTO(String token){
        setTokenAccess(token);
    }

}