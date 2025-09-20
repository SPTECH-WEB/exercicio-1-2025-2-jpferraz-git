package school.sptech.prova_ac1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario novo_usuario = repository.save(usuario);
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<List<Usuario>> buscarTodos() {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Usuario> buscarPorId(Integer id) {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Void> deletar(Integer id) {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<List<Usuario>> buscarPorDataNascimento(LocalDate nascimento) {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Usuario> atualizar(
            Integer id,
            Usuario usuario
    ) {
        return ResponseEntity.internalServerError().build();
    }
}
