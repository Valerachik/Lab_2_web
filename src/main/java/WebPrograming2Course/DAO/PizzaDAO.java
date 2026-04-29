package WebPrograming2Course.DAO;
import WebPrograming2Course.Entities.Pizza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PizzaDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    public void SavePizza(Pizza pizza){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pizza);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
    public List<Pizza> GetAllPizzas(){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pizza p", Pizza.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Pizza GetPizzaById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Pizza.class, id);
        } finally {
            em.close();
        }
    }
    public void UpdatePizzaName(int id, String Name){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pizza pizza = em.find(Pizza.class, id);
            if (pizza != null) {
                pizza.setName(Name);
                em.merge(pizza);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public void DeletePizza(int id){
        EntityManager em = emf.createEntityManager();
        try {
        em.getTransaction().begin();
        Pizza pizza = em.find(Pizza.class, id);
        em.remove(pizza);
        em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
}
