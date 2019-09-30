/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.jpqlTest;

import Entity.Employee;
import Entity.Enum.StatusUser;
import Entity.UserSuper;
import Entity.ValidatorTest.GenericTest;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Deyse
 */
public class EmployeeJPQLTest extends GenericTest{
    
    @Test
    public void allEmployees(){
        logger.info("Running allEmployees()");
        TypedQuery<UserSuper> query = em.createQuery(
        "SELECT u FROM UserSuper u WHERE TYPE(u) IN (Employee)",
        UserSuper.class
        );
        List<UserSuper> employees = query.getResultList();
        for (UserSuper employee : employees){
            System.out.print(employee);
        }
        assertEquals(8, employees.size());
    }
    
    
    @Test
    public void allActiveEmployees(){
        logger.info("Running allActiveEmployees()");
        TypedQuery<UserSuper> query = em.createQuery(
        "SELECT u FROM UserSuper u WHERE TYPE(u) IN (Employee) AND u.status LIKE :status",
         UserSuper.class
        );
        query.setParameter("status", StatusUser.ACTIVE);
        List<UserSuper> employees = query.getResultList();
        for (UserSuper employee : employees){
            System.out.println(employee);
        }
        assertEquals(8, employees.size());
    }
    
    
    @Test
    public void employeeByName(){
        logger.info("Running employeeByName()");
        TypedQuery<Employee> query = em.createQuery(
        "SELECT c FROM Employee c WHERE c.name LIKE :name",
        Employee.class
        );
        query.setParameter("name","Employee 1");
        List<Employee> employees = query.getResultList();
       
        for (Employee employee : employees){
            assertTrue(employee.getName().startsWith("Employee 1"));
            System.out.print(employee);
        }
        
        assertEquals(1, employees.size());
    }
    
    @Test
    public void employeeByCNPJ(){
        logger.info("Running employeeByCNPJ");
        TypedQuery<Employee> query = em.createQuery(
        "SELECT c FROM Employee c WHERE c.cpf LIKE :cpf",
        Employee.class
        );
        query.setParameter("cpf", "845.564.180-03");
        Employee employee = query.getSingleResult();
        System.out.println(employee);
        assertNotNull(employee);
    }
       
}
