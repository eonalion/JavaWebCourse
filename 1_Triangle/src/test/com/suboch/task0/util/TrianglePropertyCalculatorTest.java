package test.com.suboch.task0.util;

import com.suboch.task0.exception.DegenerateTriangleException;
import com.suboch.task0.shape.Triangle;
import com.suboch.task0.util.TrianglePropertyCalculator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TrianglePropertyCalculatorTest {
    @Test
    public void testForPerimeterCalculation() {
        try {
            TrianglePropertyCalculator propertyCalculator = new TrianglePropertyCalculator();
            Triangle triangle = new Triangle(0, 3, 0, 4, 1, 1);
            double actual = 7.0;
            Assert.assertEquals(actual, propertyCalculator.calculatePerimeter(triangle), 0);
        } catch (DegenerateTriangleException e) {
            Assert.fail();
        }
    }

    @Test
    public void testForSquareCalculation() throws DegenerateTriangleException {
        try {
            TrianglePropertyCalculator propertyCalculator = new TrianglePropertyCalculator();
            Triangle triangle = new Triangle(0, 3, 4, 0, 1, 1);
            double actual = 6.0;
            Assert.assertEquals(actual, propertyCalculator.calculateArea(triangle), 0);
        } catch (DegenerateTriangleException e) {
            Assert.fail();
        }
    }
}
