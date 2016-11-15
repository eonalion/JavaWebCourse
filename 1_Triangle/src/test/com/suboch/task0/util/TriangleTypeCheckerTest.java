package test.com.suboch.task0.util;

import com.suboch.task0.exception.DegenerateTriangleException;
import com.suboch.task0.shape.Triangle;
import com.suboch.task0.util.TriangleTypeChecker;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TriangleTypeCheckerTest {
    @Test(expected = DegenerateTriangleException.class)
    public void testForDegenerateTriangle() throws DegenerateTriangleException {
        new Triangle(0, 0, 0, 1, 0, 1);
    }

    @Test
    public void testForRightTriangle() {
        try {
            Triangle triangle = new Triangle(0, 3, 0, 0, 6, 0);
            Assert.assertTrue(triangle.toString() + " is not right!", TriangleTypeChecker.isRightTriangle(triangle));
        } catch (DegenerateTriangleException e) {
            Assert.fail();
        }
    }
}
