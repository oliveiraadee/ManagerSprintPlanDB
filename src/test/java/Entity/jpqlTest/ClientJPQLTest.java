/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.jpqlTest;

import Entity.Client;
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
public class ClientJPQLTest extends GenericTest{
    
    @Test
    public void allClients(){
        logger.info("Running allClients()");
        TypedQuery<UserSuper> query = em.createQuery(
        "SELECT u FROM UserSuper u WHERE TYPE(u) IN (Client)",
        UserSuper.class
        );
        List<UserSuper> clients = query.getResultList();
        for (UserSuper client : clients){
            System.out.print(client);
        }
        assertEquals(8, clients.size());
    }
    
    
    @Test
    public void allActiveClients(){
        logger.info("Running allClients()");
        TypedQuery<UserSuper> query = em.createQuery(
        "SELECT u FROM UserSuper u WHERE TYPE(u) IN (Client) AND u.status LIKE :status",
         UserSuper.class
        );
        query.setParameter("status", StatusUser.ACTIVE);
        List<UserSuper> clients = query.getResultList();
        for (UserSuper client : clients){
            System.out.print(client);
        }
        assertEquals(8, clients.size());
    }
    
    
    @Test
    public void clientByName(){
        logger.info("Running clientByName()");
        TypedQuery<Client> query = em.createQuery(
        "SELECT c FROM Client c WHERE c.name LIKE :name",
        Client.class
        );
        query.setParameter("name","Deyse Carneiro");
        List<Client> clients = query.getResultList();
       
        for (Client client : clients){
            assertTrue(client.getName().startsWith("Deyse Carneiro"));
            System.out.print(client);
        }
        
        assertEquals(1, clients.size());
    }
    
    @Test
    public void clientByCNPJ(){
        logger.info("Running clientByCNPJ");
        TypedQuery<Client> query = em.createQuery(
        "SELECT c FROM Client c WHERE c.cnpj LIKE :cnpj",
        Client.class
        );
        query.setParameter("cnpj", "94.444.303/0001-47");
        Client client = query.getSingleResult();
        System.out.println(client);
        assertNotNull(client);
    }
       
}
