package WebPrograming2Course.DAO;
import WebPrograming2Course.Entities.PizzaOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
public class PizzaOrderDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    public void SaveOrder(PizzaOrder order) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public List<PizzaOrder> GetAllOrders() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT DISTINCT o FROM PizzaOrder o " +
                    "LEFT JOIN FETCH o.pizzas " +
                    "LEFT JOIN FETCH o.customer", PizzaOrder.class).getResultList();
        } finally {
            em.close();
        }
    }
    public PizzaOrder GetOrderById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT o FROM PizzaOrder o " +
                                    "LEFT JOIN FETCH o.pizzas " +
                                    "LEFT JOIN FETCH o.customer " +
                                    "LEFT JOIN FETCH o.pizzeria " +
                                    "WHERE o.id = :id", PizzaOrder.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // Якщо замовлення не знайдено
        } finally {
            em.close();
        }
    }
    public void UpdateOrder(PizzaOrder order) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(order);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void DeleteOrder(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            PizzaOrder order = em.find(PizzaOrder.class, id);
            if (order != null) {
                em.remove(order);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
