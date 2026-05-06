package WebPrograming2Course.Entities;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;;
    @ManyToMany(mappedBy = "pizzas")
    private List<PizzaOrder> orders;
    public Pizza(){}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public List<PizzaOrder> getOrders() {
        return orders;
    }
    public void setOrders(List<PizzaOrder> orders) {
        this.orders = orders;
    }
}
