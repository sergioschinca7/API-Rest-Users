/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.dev.personas;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sergio
 */
@RestController
@RequestMapping("/challenge")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/usuarios")
    public List<Cliente> findAll() {
        return clienteServicio.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public Optional<Cliente> getCliente(@PathVariable int id) {

        Optional<Cliente> cliente = clienteServicio.findById(id);

        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado" + id);
        }
        return cliente;
    }

    @PostMapping("/usuarios")
    public Cliente addCliente(@RequestBody Cliente cliente) {
        cliente.setId(0);

        clienteServicio.guardar(cliente);

        return cliente;
    }

    @PutMapping("/usuarios")
    public Cliente updateCliente(@RequestBody Cliente cliente) {

        clienteServicio.guardar(cliente);

        return cliente;
    }

    @DeleteMapping("usuarios/{id}")
    public String deleteCliente(@PathVariable int id) {
        Optional<Cliente> cliente = clienteServicio.findById(id);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado - " + id);

        }
        clienteServicio.eliminarById(id);

        return "Eliminado el cliente - " + id;

    }
}
