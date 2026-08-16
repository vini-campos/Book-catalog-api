package br.com.vini.library.services;

import br.com.vini.library.database.repositories.ICustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final ICustomersRepository customersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
    }
}
