
package Entity.TestDB;

import Entity.Client;
import Entity.Enum.StatusUser;
import Entity.Enum.TypeUser;
import Entity.UserSuper;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Deyse
 */
public class ClientCrudTest extends GenericTest {
    @Test
    public void persistirClient() {
        logger.info("Executando persistirClient()");
        Client cliente1 = criarClient();
        em.persist(cliente1);
        em.flush();
        assertNotNull(cliente1.getId());
        assertNotNull(cliente1.getCnpj());
        assertNotNull(cliente1.getName());
        assertNotNull(cliente1.getLogin());
        assertNotNull(cliente1.getPassword());
    }
   
    @Test   
    public void atualizarCliente(){
        logger.info("Executando atualizarCliente()");
        String novoLogin = "danita";
        Long id = 7L;
        Client cliente = em.find(Client.class, id);
        cliente.setLogin(novoLogin);
        em.flush();
        String jpql = "SELECT c FROM Client c WHERE c.id = ?1";
        TypedQuery<Client> query = em.createQuery(jpql, Client.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);   
        cliente = query.getSingleResult();
        assertEquals(novoLogin, cliente.getLogin());
    }
    
    @Test
    public void atualizarClientMerge() {
        logger.info("Executando atualizarClientMerge()");
        String novoEmail = "atualizado@email.com";
        
        Long id = 2L;
        Client cliente = em.find(Client.class, id);
        cliente.setCnpj("97.847.364/0001-43");
        cliente.setLogin("Carneiro");
        cliente.setName("Deyse Carneiro");
        cliente.setState(StatusUser.ACTIVE);
        cliente.setType(TypeUser.ADMIN);
        cliente.setPassword("Pass123*");
        cliente.setEmail(novoEmail);
        em.clear();
        em.merge(cliente);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        cliente = em.find(Client.class, id, properties);
        assertEquals(novoEmail, cliente.getEmail());        
    }  
    
    @Test
    public void removerClient() {
      logger.info("Executando removerClient()");
      Client cliente = em.find(Client.class, 19L);
      em.remove(cliente);
      UserSuper usuario = em.find(UserSuper.class, 19L);
      assertNull(usuario);
    } 

  
   
    public Client criarClient() {
        Client cliente = new Client();
        cliente.setId(19L);
        cliente.setCnpj("97.847.364/0001-43");
        cliente.setLogin("Carneiro");
        cliente.setEmail("email@email.com");
        cliente.setName("Deyse Carneiro");
        cliente.setState(StatusUser.ACTIVE);
        cliente.setType(TypeUser.ADMIN);
        cliente.setPassword("Pass123*");
        return cliente;
    }
    
}


