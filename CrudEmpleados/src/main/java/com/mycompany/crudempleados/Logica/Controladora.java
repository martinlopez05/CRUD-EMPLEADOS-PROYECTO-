/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudempleados.Logica;

import com.mycompany.crudempleados.Persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Controladora {
    
    
    //llamamos a la Controladora de Persistencia
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    //metodo para eliminar un departamento por su id
    public void deleteDepartamento(int id) {
        controlPersis.deleteDepartamento(id);

    }
    
    //metodo para buscar un departamento por su id
    public Departamento findDepartamento(int id) {
        Departamento depar = controlPersis.findDepartamento(id);
        return depar;
    }
    
    //metodo para buscar un departamento por su nombre
    public Departamento findDepartamentoByNombre(String nombre) {
        Departamento depar = controlPersis.findDepartamentoByNombre(nombre);
        return depar;
    }
    
    
    //metodo para buscar todos los departamentos
    public List<Departamento> findAllDepartamentos() {
        List<Departamento> departamentos = controlPersis.findAllDepartamentos();
        return departamentos;
    }

    
    //metodo para eliminar un empleado por su id
    public void deleteEmpleado(int id) {
        controlPersis.deleteEmpleado(id);
    }
    
    //metodo para buscar un empleado por su id
    public Empleado findEmpleado(int id) {
        Empleado emple = controlPersis.findEmpleado(id);
        return emple;
    }
   
    //metodo para buscar todos los empleados
    public List<Empleado> findAllEmpleado() {
        List<Empleado> empleados = controlPersis.findAllEmpleado();
        return empleados;

    }

    //metodos para crear un departamento
    public void guardarDepartamento(String nombre) {
        Departamento depar = new Departamento(nombre);
        controlPersis.createDepartamento(depar);
    }
    
    //metodo para crear un empleado
    public void guardarEmpleado(String nombre, String apellido, int edad, String dni, String email, String celular, String sexo, Departamento depar) {
        Empleado empleado = new Empleado(nombre, apellido, edad, dni, email, celular, sexo, depar);
        controlPersis.createEmpleado(empleado);
    }
    
    //metodo para buscar a todos los empleados por departamentos
    public List<Empleado> findEmpleadosbyDepartamento(String nombreDepartamento) {
        List<Departamento> departamentos = this.findAllDepartamentos();
        for (Departamento depar : departamentos) {
            if (nombreDepartamento.equals(depar.getNombre())) {
                return depar.getEmpleados();
            }
        }
        return null;

    }
    
    //metodo para editar un empleado
    public void editEmpleado(Empleado empleado, String nombre, String apellido, int edad, String dni, String email, String celular, String sexo, Departamento depar) {
        if (empleado != null) {
            empleado.setApellido(apellido);
            empleado.setCelular(celular);
            empleado.setDepartamento(depar);
            empleado.setDni(dni);
            empleado.setEdad(edad);
            empleado.setEmail(email);
            empleado.setNombre(nombre);
            empleado.setSexo(sexo);

            
            controlPersis.editEmpleado(empleado);
        }    
    } 
    
    //metodo para editar un departamento
    public void editDepartamento(Departamento depar, String nombre) {
        depar.setNombre(nombre);
        controlPersis.editDepartamento(depar);
    }
    
    
}
  
    
