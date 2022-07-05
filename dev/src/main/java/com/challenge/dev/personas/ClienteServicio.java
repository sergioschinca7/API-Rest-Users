/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.dev.personas;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sergio
 */
public interface ClienteServicio {
    
    public List<Cliente> findAll();
    
    public Optional<Cliente> findById(int id);
    
    public void guardar(Cliente cliente);
    
    public void eliminarById(int id);
    
}
