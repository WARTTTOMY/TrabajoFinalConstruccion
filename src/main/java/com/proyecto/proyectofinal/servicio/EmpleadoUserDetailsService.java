package com.proyecto.proyectofinal.servicio;

import com.proyecto.proyectofinal.modelo.Empleado;
import com.proyecto.proyectofinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByNombreEmpleado(username);
        if (empleado == null) {
            throw new UsernameNotFoundException("Empleado no encontrado");
        }
        return User.withUsername(empleado.getNombreEmpleado())
                .password("{noop}generalPassword") // Contraseña general para todos los empleados
                .roles("EMPLOYEE")
                .build();
    }
}
