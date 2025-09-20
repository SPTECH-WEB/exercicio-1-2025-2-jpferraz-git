package school.sptech.prova_ac1;

import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<Usuario> criar(@RequestBody Usuario novo_usuario) {

        List<Usuario> usuarios = repository.findAll();

        for (Usuario usuario_atual : usuarios) {
            if (usuario_atual.getCpf().equals(novo_usuario.getCpf()) ||
                usuario_atual.getEmail().equals(novo_usuario.getEmail())){
                return ResponseEntity.status(409).build();
            }
        }
        repository.save(novo_usuario);
        return ResponseEntity.status(201).body(novo_usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> usuarios = repository.findAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(repository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        List<Usuario> usuarios = repository.findAll();
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)){
                return ResponseEntity.ok(usuario);
            }
        }
//        Usuario user = repository.findAll().get(id);

        return ResponseEntity.status(404).build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        List<Usuario> usuarios = repository.findAll();
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)){
                repository.delete(usuario);
                return ResponseEntity.status(204).build();
            }
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/filtro-data")
    public ResponseEntity<List<Usuario>> buscarPorDataNascimento(
            @RequestParam LocalDate nascimento) {

        List<Usuario> usuarios = repository.findAll();
        List<Usuario> user_nascimento = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getDataNascimento().isAfter(nascimento)) {
                user_nascimento.add(usuario);
            }
        }

        if (user_nascimento.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(user_nascimento);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable Integer id,
            @RequestBody Usuario usuarioAtualizado
    ) {

        Usuario usuario = repository.findById(Long.valueOf(id)).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // 404
        }

        for (Usuario u : repository.findAll()) {
            if (!u.getId().equals(id) &&
                    (u.getEmail().equals(usuarioAtualizado.getEmail()) ||
                            u.getCpf().equals(usuarioAtualizado.getCpf()))) {
                return ResponseEntity.status(409).build(); // 409 conflito
            }
        }

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setCpf(usuarioAtualizado.getCpf());
        usuario.setSenha(usuarioAtualizado.getSenha());
        usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());

        repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

}
