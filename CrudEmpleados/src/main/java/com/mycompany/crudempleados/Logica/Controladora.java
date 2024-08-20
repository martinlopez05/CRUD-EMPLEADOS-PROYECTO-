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

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void deleteDepartamento(int id) {
        controlPersis.deleteDepartamento(id);

    }

    public Departamento findDepartamento(int id) {
        Departamento depar = controlPersis.findDepartamento(id);
        return depar;
    }

    public Departamento findDepartamentoByNombre(String nombre) {
        Departamento depar = controlPersis.findDepartamentoByNombre(nombre);
        return depar;
    }

    public List<Departamento> findAllDepartamentos() {
        List<Departamento> departamentos = controlPersis.findAllDepartamentos();
        return departamentos;
    }

    public void editDepartamento(Departamento depar) {
        controlPersis.editDepartamento(depar);
    }

    public void deleteEmpleado(int id) {
        controlPersis.deleteEmpleado(id);
    }

    public Empleado findEmpleado(int id) {
        Empleado emple = controlPersis.findEmpleado(id);
        return emple;
    }

    public List<Empleado> findAllEmpleado() {
        List<Empleado> empleados = controlPersis.findAllEmpleado();
        return empleados;

    }

    
    public void guardarDepartamento(String nombre) {
        Departamento depar = new Departamento(nombre);
        controlPersis.createDepartamento(depar);
    }

    public void guardarEmpleado(String nombre, String apellido, int edad, String dni, String email, String celular, String sexo, Departamento depar) {
        Empleado empleado = new Empleado(nombre, apellido, edad, dni, email, celular, sexo, depar);
        controlPersis.createEmpleado(empleado);
    }

    public List<Empleado> findEmpleadosbyDepartamento(String nombreDepartamento) {
        List<Departamento> departamentos = this.findAllDepartamentos();
        for (Departamento depar : departamentos) {
            if (nombreDepartamento.equals(depar.getNombre())) {
                return depar.getEmpleados();
            }
        }
        return null;

    }

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
    
    

}
