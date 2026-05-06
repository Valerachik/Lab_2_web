package WebPrograming2Course.Entities;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pizzerias")
public class Pizzeria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "pizzeria")
    private List<PizzaOrder> orders;
    public Pizzeria(){}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<PizzaOrder> getOrders() {
        return orders;
    }
    public void setOrders(List<PizzaOrder> orders) {
        this.orders = orders;
    }
}
