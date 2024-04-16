package com.pokemonreview.api.presentation.pokemon;

import com.pokemonreview.api.presentation.pokemon.dto.PokemonDto;
import com.pokemonreview.api.presentation.pokemon.pagination.PokemonGetAllResponse;
import com.pokemonreview.api.application.service.pokemon.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class PokemonController {
    
    PokemonService pokemonService;
    
    
    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }
    
    
    @GetMapping("/pokemon")
    public ResponseEntity<PokemonGetAllResponse> getAllPokemons(
            @RequestParam(value = "page", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "size", defaultValue = "5", required = false) int pageSize
    ) {
        
        return ResponseEntity.ok(pokemonService.getAllPokemon(pageNumber, pageSize));
    }
    
    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemon(
            @PathVariable int id
    ) {
        return new ResponseEntity<>(
                pokemonService.getPokemonById(id),
                HttpStatus.OK
                );
    }
    
    @PostMapping("/pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        return new ResponseEntity <>(
                pokemonService.createPokemon(pokemonDto),
                HttpStatus.CREATED);
    }
    
    @PutMapping("/pokemon/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PokemonDto> updatePokemon(
            @PathVariable int id,
            @RequestBody PokemonDto pokemonDto
    ) {
        return ResponseEntity.ok(pokemonService.updatePokemonById(id, pokemonDto));
    }
    
    @DeleteMapping("/pokemon/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deletePokemon(
            @PathVariable("id") int pokemonId
    ) {
        pokemonService.deletePokemonById(pokemonId);
        return ResponseEntity.ok("Successfully deleted pokemon with id: " + pokemonId);
    }
}
