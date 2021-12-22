package models;

import services.JsonService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Контейнер для блюда в json формате.
 */
@Entity
@Table(name = "dishes")
public class JSONDish {
    @Id
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "json")
    private String jsonString;

    protected JSONDish() {
    }

    public JSONDish(Dish dish) {
        this.title = dish.getTitle();
        this.jsonString = JsonService.getStringFromDish(dish);
    }

    /**
     * @return блюдо, созданное из json.
     */
    public Dish getDish() {
        return JsonService.getDishFromJsonString(jsonString);
    }

    public String getTitle() {
        return title;
    }

    public String getJsonString() {
        return jsonString;
    }
}
