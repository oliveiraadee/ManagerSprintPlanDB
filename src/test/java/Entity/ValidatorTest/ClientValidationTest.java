package Entity.ValidatorTest;


import Entity.Client;
import Entity.Enum.StatusUser;
import Entity.Enum.TypeUser;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Deyse
 */
public class ClientValidationTest extends GenericTest{

  
    @Test(expected = ConstraintViolationException.class)
    public void persistirClientInvalido() {
        Client cliente = null;
        try {
            cliente = new Client();
            cliente.setCnpj("258.171.482-34"); //CPF inválido
            cliente.setEmail("email_invalido@"); //E-mail inválido
            cliente.setLogin("fulano_silva");
            cliente.setPassword("papa");
            cliente.setName("joaninha");
            cliente.setState(StatusUser.ACTIVE);
            cliente.setType(TypeUser.ADMIN);
            em.persist(cliente);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class Entity.Client.cnpj: CNPJ inválido"),
                                startsWith("class Entity.Client.email: Não é um endereço de e-mail"),
                                startsWith("class Entity.Client.login: tamanho deve estar entre 6 e 10"),
                                startsWith("class Entity.Client.password: A senha deve possuir pelo menos um caractere de: pontuação, maiúscula, minúscula e número.")
                                )
                );
            }

            assertEquals(4, constraintViolations.size());
            assertNull(null);
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarClientInvalido() {
        TypedQuery<Client> query = em.createQuery("SELECT v FROM Client v WHERE v.cnpj like :cnpj", Client.class);
        query.setParameter("cnpj", "94.444.303/0001-47");
        Client cliente = query.getSingleResult();
        cliente.setEmail("email@");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("Não é um endereço de e-mail", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
