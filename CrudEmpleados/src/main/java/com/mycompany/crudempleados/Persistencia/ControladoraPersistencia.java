/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudempleados.Persistencia;

import com.mycompany.crudempleados.Logica.Departamento;
import com.mycompany.crudempleados.Logica.Empleado;
import com.mycompany.crudempleados.Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ControladoraPersistencia {
    
    DepartamentoJpaController deparJpa = new DepartamentoJpaController();
    EmpleadoJpaController empleJpa = new EmpleadoJpaController();

    public void createDepartamento(Departamento depar) {
        deparJpa.create(depar);
    }

    public void deleteDepartamento(int id) {
        try {
            deparJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Departamento findDepartamento(int id) {
        return deparJpa.findDepartamento(id);
    }

    public void editDepartamento(Departamento depar) {
        try {
            deparJpa.edit(depar);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    public void deleteEmpleado(int id) {
        try {
            empleJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public Empleado findEmpleado(int id) {
        return empleJpa.findEmpleado(id);
    }
    

    public List<Empleado> findAllEmpleado() {
        return empleJpa.findEmpleadoEntities();
    }

    public void editEmpleado(Empleado emple) {
        try {
            empleJpa.edit(emple);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Departamento> findAllDepartamentos() {
        return deparJpa.findDepartamentoEntities();
    }

    public void createEmpleado(Empleado emple) {
        empleJpa.create(emple);
    }

    public Departamento findDepartamentoByNombre(String nombre) {
        return deparJpa.findDepartamentoByNombre(nombre);
    }
    
    
    
}
