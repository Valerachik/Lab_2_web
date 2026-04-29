package WebPrograming2Course.DAO;
import WebPrograming2Course.Entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class CustomerDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    public void SaveCustomer(Customer customer){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
    public List<Customer> GetAllCustomers(){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        } finally {
            em.close();
        }
    }
    public void DeleteCustomer(int id){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            em.remove(customer);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
    public void UpdateCustomerName(int id, String Name){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                customer.setName(Name);
                em.merge(customer);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public void UpdateCustomerSurName(int id, String SurName){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                customer.setSurname(SurName);
                em.merge(customer);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public Customer GetCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }
    public void UpdateCustomerPhone(int id, String Phone){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                customer.setPhone(Phone);
                em.merge(customer);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
