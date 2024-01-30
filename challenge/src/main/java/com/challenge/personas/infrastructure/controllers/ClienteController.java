/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.personas.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import com.challenge.notificacion.domain.Notificacion;
import com.challenge.notificacion.domain.NotificacionHandler;
import com.challenge.notificacion.infrastructure.controllers.GrettingController;
import com.challenge.personas.domain.Cliente;
import com.challenge.personas.domain.service.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

@RestController
@RequestMapping("/")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private GrettingController grettingController;

    @Autowired
    private NotificacionHandler notificacionHandler;

    @GetMapping("/clientes")
    public List<Cliente> findAll() {
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
    public Cliente addCliente(@RequestBody Cliente cliente) throws Exception {

        clienteServicio.guardar(cliente);

        notificacionHandler.sendMessage(new TextMessage("usuario dado de alta"));

        return cliente;
    }

    @PutMapping("/modificar")
    public Cliente updateCliente(@RequestBody Cliente cliente) {

        clienteServicio.guardar(cliente);

        return cliente;
    }

    @DeleteMapping("delete/{id}")
    public String deleteCliente(@PathVariable int id) {
        Optional<Cliente> cliente = clienteServicio.findById(id);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado - " + id);

        }
        clienteServicio.eliminarById(id);

        return "Eliminado el cliente - " + id;

    }
}
