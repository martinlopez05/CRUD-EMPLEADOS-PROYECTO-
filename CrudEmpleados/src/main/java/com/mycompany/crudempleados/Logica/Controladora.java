/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudempleados.Logica;

import com.mycompany.crudempleados.Persistencia.ControladoraPersistencia;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Controladora {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    
    
    public void deleteDepartamento(int id){
        controlPersis.deleteDepartamento(id);
        
    }
    
    public Departamento findDepartamento(int id){
        Departamento depar = controlPersis.findDepartamento(id);
        return depar;
    }
    
    public Departamento findDepartamentoByNombre(String nombre){
        Departamento depar = controlPersis.findDepartamentoByNombre(nombre);
        return depar;
    }
    
    public List<Departamento> findAllDepartamentos(){
        List<Departamento> departamentos = controlPersis.findAllDepartamentos();
        return departamentos;
    }
    
    public void editDepartamento(Departamento depar){
        controlPersis.editDepartamento(depar);
    }
    
    
    
    public void deleteEmpleado(int id){
        controlPersis.deleteEmpleado(id);
    }
    
    public Empleado findEmpleado(int id){
        Empleado emple = controlPersis.findEmpleado(id);
        return emple;
    }
    
    public List<Empleado> findAllEmpleado(){
        List<Empleado> empleados = controlPersis.findAllEmpleado();
        return empleados;
        
    }
    
    public void editEmpleado(Empleado emple){
        controlPersis.editEmpleado(emple);
    }

    public void guardarDepartamento(String nombre) {
        Departamento depar = new Departamento(nombre);
        controlPersis.createDepartamento(depar);
    }

    public void guardarEmpleado(String nombre, String apellido, int edad, String dni, String email, String celular, String sexo, Departamento depar) {
        Empleado empleado = new Empleado(nombre,apellido,edad,dni,email,celular,sexo,depar);
        controlPersis.createEmpleado(empleado);
    }
    
    
    
    
}
