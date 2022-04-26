package br.com.zup.edu.tech.store.api.produto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovoProdutoController {

    private final ProdutoRepository repository;

    public CadastrarNovoProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/produtos")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByCodigo(request.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Produto já cadastrado");
        }

        Produto produto = request.toProduto();

        repository.save(produto);

        URI location = uriComponentsBuilder.path("/produtos/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
