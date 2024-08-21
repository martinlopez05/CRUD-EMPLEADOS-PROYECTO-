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
    
    //llamamos a los Jpa de cada clase
    DepartamentoJpaController deparJpa = new DepartamentoJpaController();
    EmpleadoJpaController empleJpa = new EmpleadoJpaController();
    
    //metodo para crear un departamento
    public void createDepartamento(Departamento depar) {
        deparJpa.create(depar);
    }
    
    //metodo para eliminar un departamento por su id
    public void deleteDepartamento(int id) {
        try {
            deparJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //metodo para buscar un departamento por su id
    public Departamento findDepartamento(int id) {
        return deparJpa.findDepartamento(id);
    }
    
    //metodo para editar un departamento
    public void editDepartamento(Departamento depar) {
        try {
            deparJpa.edit(depar);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //metodo para eliminar un empleado por su id
    public void deleteEmpleado(int id) {
        try {
            empleJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    //metodo para buscar un empleado por su id
    public Empleado findEmpleado(int id) {
        return empleJpa.findEmpleado(id);
    }
    
    //metodo para buscar todos los empleados
    public List<Empleado> findAllEmpleado() {
        return empleJpa.findEmpleadoEntities();
    }
    
    //metodo para editar un empleado
    public void editEmpleado(Empleado emple) {
        try {
            empleJpa.edit(emple);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    //metodo para buscar todos los departamentos
    public List<Departamento> findAllDepartamentos() {
        return deparJpa.findDepartamentoEntities();
    }
    
    //metodo para crear un departamento
    public void createEmpleado(Empleado emple) {
        empleJpa.create(emple);
    }
    
    //metodo para buscar un departamento por su nombre
    public Departamento findDepartamentoByNombre(String nombre) {
        return deparJpa.findDepartamentoByNombre(nombre);
    }
    
    
    
}
