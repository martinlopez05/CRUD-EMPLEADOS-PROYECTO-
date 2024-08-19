/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudempleados.Persistencia;

import com.mycompany.crudempleados.Logica.Departamento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.crudempleados.Logica.Empleado;
import com.mycompany.crudempleados.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author Usuario
 */
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public DepartamentoJpaController() {
        emf= Persistence.createEntityManagerFactory("CrudEmpleadosPU");
    }
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamento departamento) {
        if (departamento.getEmpleados() == null) {
            departamento.setEmpleados(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Empleado> attachedEmpleados = new ArrayList<Empleado>();
            for (Empleado empleadosEmpleadoToAttach : departamento.getEmpleados()) {
                empleadosEmpleadoToAttach = em.getReference(empleadosEmpleadoToAttach.getClass(), empleadosEmpleadoToAttach.getId());
                attachedEmpleados.add(empleadosEmpleadoToAttach);
            }
            departamento.setEmpleados(attachedEmpleados);
            em.persist(departamento);
            for (Empleado empleadosEmpleado : departamento.getEmpleados()) {
                Departamento oldDepartamentoOfEmpleadosEmpleado = empleadosEmpleado.getDepartamento();
                empleadosEmpleado.setDepartamento(departamento);
                empleadosEmpleado = em.merge(empleadosEmpleado);
                if (oldDepartamentoOfEmpleadosEmpleado != null) {
                    oldDepartamentoOfEmpleadosEmpleado.getEmpleados().remove(empleadosEmpleado);
                    oldDepartamentoOfEmpleadosEmpleado = em.merge(oldDepartamentoOfEmpleadosEmpleado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamento departamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getId());
            List<Empleado> empleadosOld = persistentDepartamento.getEmpleados();
            List<Empleado> empleadosNew = departamento.getEmpleados();
            ArrayList<Empleado> attachedEmpleadosNew = new ArrayList<Empleado>();
            for (Empleado empleadosNewEmpleadoToAttach : empleadosNew) {
                empleadosNewEmpleadoToAttach = em.getReference(empleadosNewEmpleadoToAttach.getClass(), empleadosNewEmpleadoToAttach.getId());
                attachedEmpleadosNew.add(empleadosNewEmpleadoToAttach);
            }
            empleadosNew = attachedEmpleadosNew;
            departamento.setEmpleados(empleadosNew);
            departamento = em.merge(departamento);
            for (Empleado empleadosOldEmpleado : empleadosOld) {
                if (!empleadosNew.contains(empleadosOldEmpleado)) {
                    empleadosOldEmpleado.setDepartamento(null);
                    empleadosOldEmpleado = em.merge(empleadosOldEmpleado);
                }
            }
            for (Empleado empleadosNewEmpleado : empleadosNew) {
                if (!empleadosOld.contains(empleadosNewEmpleado)) {
                    Departamento oldDepartamentoOfEmpleadosNewEmpleado = empleadosNewEmpleado.getDepartamento();
                    empleadosNewEmpleado.setDepartamento(departamento);
                    empleadosNewEmpleado = em.merge(empleadosNewEmpleado);
                    if (oldDepartamentoOfEmpleadosNewEmpleado != null && !oldDepartamentoOfEmpleadosNewEmpleado.equals(departamento)) {
                        oldDepartamentoOfEmpleadosNewEmpleado.getEmpleados().remove(empleadosNewEmpleado);
                        oldDepartamentoOfEmpleadosNewEmpleado = em.merge(oldDepartamentoOfEmpleadosNewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = departamento.getId();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            List<Empleado> empleados = departamento.getEmpleados();
            for (Empleado empleadosEmpleado : empleados) {
                empleadosEmpleado.setDepartamento(null);
                empleadosEmpleado = em.merge(empleadosEmpleado);
            }
            em.remove(departamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Departamento findDepartamento(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }
    
    
    public Departamento findDepartamentoByNombre(String nombre) {
    EntityManager em = getEntityManager();
    try {
        return em.createQuery("SELECT d FROM Departamento d WHERE d.nombre = :nombre", Departamento.class)
                 .setParameter("nombre", nombre)
                 .getSingleResult();
    } catch (NoResultException e) {
        return null; 
    } finally {
        em.close();
    }
}


    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
