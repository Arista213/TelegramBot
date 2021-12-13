package model;

import java.util.Objects;

public class Ingredient {
    private final String title;

    public Ingredient(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(title.toLowerCase(), that.title.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "title='" + title + '\'' +
                '}';
    }
}
