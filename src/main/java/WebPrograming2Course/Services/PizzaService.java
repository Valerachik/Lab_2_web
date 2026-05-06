package WebPrograming2Course.Services;
import WebPrograming2Course.DAO.PizzaDAO;
import WebPrograming2Course.Entities.Pizza;
import java.util.List;

public class PizzaService {
    private PizzaDAO pizzaDAO = new PizzaDAO();

    public List<Pizza> getAllPizzas() {
        return pizzaDAO.GetAllPizzas();
    }
    public void SavePizza(Pizza pizza) {
        pizzaDAO.SavePizza(pizza);
    }
    public void UpdatePizzaName(int id, String name) {
        pizzaDAO.UpdatePizzaName(id, name);
    }
    public void deletePizza(int id) {
        pizzaDAO.DeletePizza(id);
    }
    public Pizza GetPizzaById(int id){
       return pizzaDAO.GetPizzaById(id);
    }
}
