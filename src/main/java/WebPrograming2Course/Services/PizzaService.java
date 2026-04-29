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
    public void UpdatePizzaName(Pizza pizza) {
        pizzaDAO.UpdatePizzaName(pizza.getId(), pizza.getName());
    }
    public void deletePizza(int id) {
        pizzaDAO.DeletePizza(id);
    }
}
