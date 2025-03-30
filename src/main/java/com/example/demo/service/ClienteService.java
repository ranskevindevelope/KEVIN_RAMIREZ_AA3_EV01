package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> ObtenerTodasLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente guardarcliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarcliente(Integer idCliente, Cliente cliente)
    {
    Optional<Cliente> clienteExiste = clienteRepository.findById(idCliente.longValue());
    if(clienteExiste.isPresent()){
        Cliente clienteActual = clienteExiste.get();
        clienteActual.setNIT(cliente.getNIT());
        clienteActual.setRazon_social(cliente.getRazon_social());
        clienteActual.setTelefono(cliente.getTelefono());
        clienteActual.setDireccion(cliente.getDireccion());
        return clienteRepository.save(clienteActual);
    }
    throw new RuntimeException("No existe ningun cliente" + idCliente);
    }

    public void eliminarcliente(Integer id)
    {
        if(!clienteRepository.existsById(id.longValue()))
            throw new RuntimeException("No existe ningun cliente" + id + " no existe mi compa");
        else{
            System.out.println("Eliminando cliente..." + id);
        }
        clienteRepository.deleteById(id.longValue());
    }
}











