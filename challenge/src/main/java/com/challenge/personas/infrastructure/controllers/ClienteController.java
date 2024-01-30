/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.personas.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import com.challenge.personas.domain.Cliente;
import com.challenge.personas.domain.service.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/clientes")
    public List<Cliente> findAll() {
        simpMessagingTemplate.convertAndSend("/topic/abm", "Requeridos todos los usuarios"
                );
        return clienteServicio.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Optional<Cliente> getCliente(@PathVariable int id) {

        Optional<Cliente> cliente = clienteServicio.findById(id);

        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado" + id);
        }
        return cliente;
    }

    @PostMapping("/save")
    public ResponseEntity<String> addCliente(@RequestBody Cliente cliente) {
        try {
            clienteServicio.guardar(cliente);

            simpMessagingTemplate.convertAndSend("/topic/abm", "Nuevo cliente guardado: " + cliente.getNombre());

            return ResponseEntity.ok("Cliente guardado exitosamente");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el cliente");
        }
    }

    @PutMapping("/modificar")
    public Cliente updateCliente(@RequestBody Cliente cliente) {

        clienteServicio.guardar(cliente);

        simpMessagingTemplate.convertAndSend("/topic/abm", "Cliente modificado "
                +cliente.getId());

        return cliente;
    }

    @DeleteMapping("delete/{id}")
    public String deleteCliente(@PathVariable int id) {
        Optional<Cliente> cliente = clienteServicio.findById(id);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado - " + id);

        }
        clienteServicio.eliminarById(id);

        simpMessagingTemplate.convertAndSend("/topic/abm", "Cliente eliminado"
                +cliente.get().getId());

        return "Eliminado el cliente - " + id;

    }
}
