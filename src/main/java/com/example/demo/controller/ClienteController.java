package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    public List<Cliente> obtenerClientes() {
        return clienteService.ObtenerTodasLosClientes();
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarcliente(cliente); return ResponseEntity.ok(nuevoCliente);
    }
    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer idCliente,
                                                     @RequestBody Cliente cliente) {
        Cliente clienteActual = clienteService.actualizarcliente(idCliente, cliente);
        return ResponseEntity.ok(clienteActual);
    }

    @DeleteMapping("/{clienteId}")
public ResponseEntity<Map<String,String>> eliminarCliente(@PathVariable Integer clienteId) {
        clienteService.eliminarcliente(clienteId);
        Map<String, String> ErrorMiCompa = new HashMap<>();
        ErrorMiCompa.put("mensaje 200ok", "Cliente eliminando..." + clienteId + "eliminado exitosamente");
        return ResponseEntity.ok(ErrorMiCompa);
    }

    @ExceptionHandler (RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException exception) {
        Map<String, String> ErrorMiCompa = new HashMap<>();
        ErrorMiCompa.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMiCompa);
    }

}



















