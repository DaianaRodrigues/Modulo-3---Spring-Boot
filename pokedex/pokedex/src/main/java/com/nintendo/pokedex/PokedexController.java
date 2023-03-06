package com.nintendo.pokedex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

//Essa parte que diz que ele é um Controler
@RestController
//estamos dizendo qual o mapeamento, qual é o url que esse Controller ira responder
@RequestMapping("/pokemons")
public class PokedexController {
    //Criando uma lista para gravar os dados na memoria
    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private int ultimoId = 1;

    //estamos dizendo qual o mapeamento, qual é o url que esse Controller ira responder
    @GetMapping
    public ArrayList<Pokemon> listar(){
        return pokemons;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //Os dados recebidos vem dentro do @RequestBody
    public void cadastrar(@RequestBody Pokemon pokemon) {
        pokemon.setId(ultimoId);
        pokemons.add(pokemon);
        ultimoId++;
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id){
        Pokemon pokemonEncontrado = null;

        for(Pokemon pokemon : pokemons){
            if(pokemon.getId() == id) {
                pokemonEncontrado = pokemon;
                break;
            }
        }

        if(pokemonEncontrado != null){
            pokemons.remove(pokemonEncontrado);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
