/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.TestDB;

import Entity.UserSuper;
import Entity.Employee;
import Entity.Enum.StatusUser;
import Entity.Enum.TypeUser;
import static Entity.TestDB.GenericTest.logger;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author Deyse
 */
public class EmployeeCrudTest extends GenericTest {
    @Test
    public void persistirEmployee() {
        logger.info("Executando persistirEmployee()");
        Employee employee = criarEmployee();
        em.persist(employee);
        em.flush();
        assertNotNull(employee.getId());
        assertNotNull(employee.getName());
        assertNotNull(employee.getPassword());
        assertNotNull(employee.getLogin());
    }
    
    @Test
      public void atualizarEmployee(){
        logger.info("Executando atualizarEmployee()");
        String novoLogin = "danita";
        Long id = 15L;
        Employee employee = em.find(Employee.class, id);
        employee.setLogin(novoLogin);
        em.flush();
        String jpql = "SELECT E FROM Employee E WHERE E.id = ?1";
        TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);   
        employee = query.getSingleResult();
        assertEquals(novoLogin, employee.getLogin());
    }
      
    @Test
    public void atualizarEmployeeMerge() {
        logger.info("Executando atualizarEmployeeMerge()");
        String novoEmail = "atualizado@email.com";
        Long id = 9L;
        Employee employee = em.find(Employee.class, id);
        employee.setCpf("118.033.720-44");
        employee.setLogin("Carneiro");
        employee.setName("Deyse Carneiro");
        employee.setState(StatusUser.ACTIVE);
        employee.setType(TypeUser.ADMIN);
        employee.setPassword("Pass123*");
        employee.setEmail(novoEmail);
        em.clear();
        em.merge(employee);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        employee = em.find(Employee.class, id, properties);
        assertEquals(novoEmail, employee.getEmail());        
    }  
      
   @Test
    public void removerEmployee() {
        logger.info("Executando removerEmployee()");
        Employee employee = em.find(Employee.class, 19L);
        em.remove(employee);
        UserSuper usuario = em.find(UserSuper.class, 19L);
        assertNull(usuario);
    } 
    
    
    
    public Employee criarEmployee() {
        Employee employee = new Employee();
        employee.setId(19L);
        employee.setCpf("706.751.384-77");
        employee.setLogin("Carneiro");
        employee.setEmail("email@email.com");
        employee.setName("Deyse Carneiro");
        employee.setState(StatusUser.ACTIVE);
        employee.setType(TypeUser.ADMIN);
        employee.setPassword("Pass123*");
        return employee;
    }
    
}


