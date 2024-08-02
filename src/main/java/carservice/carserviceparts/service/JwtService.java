package carservice.carserviceparts.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    UserDetails extractUserDetails(String jwtToken);
}
