package praktikum;

import org.junit.Test;

import java.util.Arrays;

import  static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;



public class BurgerTest {
    @Test
    public void setBunsShouldSetBun() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void removeIngredientShouldRemoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertFalse(burger.ingredients.contains(ingredient));
    }

    @Test
    public void moveIngredientShouldMoveIngredient() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(Arrays.asList(secondIngredient, firstIngredient),burger.ingredients);
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(100f);
        when(firstIngredient.getPrice()).thenReturn(50f);
        when(secondIngredient.getPrice()).thenReturn(200f);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float actualPrice = burger.getPrice();

        assertEquals(450f, actualPrice, 0.0f);
    }

}
