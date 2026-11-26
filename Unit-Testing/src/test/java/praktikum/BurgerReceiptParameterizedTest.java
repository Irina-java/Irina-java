package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTest {
    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerReceiptParameterizedTest(String bunName, float bunPrice, IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;

    }

    @Parameterized.Parameters(name = "Булка: {0}, ингредиент: {3}")
    public static Collection<Object[]>getTestData() {
        return Arrays.asList(new Object[][]{
                {"Белая булка", 100F, IngredientType.FILLING, "Котлета", 200F},
                {"Черная булка", 150F, IngredientType.SAUCE, "Острый соус", 50F}
        });
    }
    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getType()).thenReturn(ingredientType);
        when(ingredient.getName()).thenReturn(ingredientName);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float expectedPrice = bunPrice * 2 + ingredientPrice;

        String expectedReceipt = String.format("(==== %s ====)%n", bunName)
                + String.format("= %s %s =%n", ingredientType.toString().toLowerCase(), ingredientName)
                + String.format("(==== %s ====)%n",  bunName)
                + String.format("%nPrice: %f%n", expectedPrice);

        assertEquals(expectedReceipt, burger.getReceipt());

    }

}
