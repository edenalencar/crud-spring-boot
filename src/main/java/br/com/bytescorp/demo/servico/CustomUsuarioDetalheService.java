package br.com.bytescorp.demo.servico;

import br.com.bytescorp.demo.model.Usuario;
import br.com.bytescorp.demo.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUsuarioDetalheService implements UserDetailsService {
    @Autowired
    private final UsuarioRepositorio usuarioRepositorio;

    public CustomUsuarioDetalheService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        final Usuario usuario = Optional.ofNullable(usuarioRepositorio.findByNomeUsuario(nomeUsuario)).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        final List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        final List<GrantedAuthority> authorityListUsuario = AuthorityUtils.createAuthorityList("ROLE_USER");
        return new User(usuario.getNomeUsuario(), usuario.getSenha(), usuario.isAdmin() ? authorityListAdmin : authorityListUsuario);
    }
}
