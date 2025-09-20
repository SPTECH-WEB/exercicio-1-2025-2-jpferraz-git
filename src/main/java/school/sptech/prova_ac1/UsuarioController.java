package school.sptech.prova_ac1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
        return ResponseEntity.ok(novo_usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(repository.findAll().get(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Usuario removido = repository.findAll().get(id);
        repository.delete(removido);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{nascimento}")
    public ResponseEntity<List<Usuario>> buscarPorDataNascimento(@PathVariable LocalDate nascimento) {

        List<Usuario> usuarios = repository.findAll();
        List<Usuario> user_nascimento = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getDataNascimento().equals(nascimento)){
                user_nascimento.add(usuario);
            }
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Usuario> atualizar(
            Integer id,
            Usuario usuario
    ) {
        return ResponseEntity.internalServerError().build();
    }
}
