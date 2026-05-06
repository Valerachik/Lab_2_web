package WebPrograming2Course.DAO;
import WebPrograming2Course.Entities.Pizzeria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PizzeriaDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    public void SavePizzeria(Pizzeria pizzeria){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pizzeria);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
    public List<Pizzeria> GetAllPizzerias(){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pizzeria p", Pizzeria.class).getResultList();
        } finally {
            em.close();
        }
    }
    public void DeletePizzeria(int id){
        EntityManager em = emf.createEntityManager();
        try {
        em.getTransaction().begin();
        Pizzeria pizzeria = em.find(Pizzeria.class, id);
        em.remove(pizzeria);
        em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
    public void UpdatePizzeriaName(int id, String Name){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pizzeria pizzeria = em.find(Pizzeria.class, id);
            if (pizzeria != null) {
                pizzeria.setName(Name);
                em.merge(pizzeria);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public Pizzeria GetPizzeriaById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Pizzeria.class, id);
        } finally {
            em.close();
        }
    }
}
