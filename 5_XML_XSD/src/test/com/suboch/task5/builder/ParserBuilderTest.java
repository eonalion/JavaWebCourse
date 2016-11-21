package test.com.suboch.task5.builder;


import com.suboch.task5.builder.FlowerStAXBuilder;
import com.suboch.task5.builder.FlowersDOMBuilder;
import com.suboch.task5.builder.FlowersSAXBuilder;
import com.suboch.task5.flower.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ParserBuilderTest {
    private List<Flower> expectedFlowerList;
    private List<Flower> actualFlowerList;
    private static final Path FILE_PATH = Paths.get("data", "test.xml");

    @Before
    public void initialize() {
        expectedFlowerList = new ArrayList<>();
        expectedFlowerList.add(new IndoorFlower("Indoor flower 1", "Origin1", SoilType.LOAM, new VisualParameters("color1", "color2", 230), new GrowingTips(true, 20, 500), MultiplyingType.SEEDS, true));
        expectedFlowerList.add(new OutdoorFlower("Outdoor flower 1", "Origin1", SoilType.PODZOLIC, new VisualParameters("color1", "color2", 500), new GrowingTips(false, 15, 500), MultiplyingType.CUTTINGS, FlowerLifetime.PERENNIAL));
        expectedFlowerList.add(new IndoorFlower("Indoor flower 2", "Origin1", SoilType.SOD_PODZOLIC, new VisualParameters("color1", "color2", 200), new GrowingTips(true, 15, 200), MultiplyingType.LEAVES, false));
    }

    @Test
    public void testSAXParser() {
        FlowersSAXBuilder flowersSAXBuilder = new FlowersSAXBuilder();
        flowersSAXBuilder.buildFlowersList(FILE_PATH.toFile().getPath());
        actualFlowerList = flowersSAXBuilder.getFlowers();
        Assert.assertTrue(expectedFlowerList.equals(expectedFlowerList));
    }

    @Test
    public void testDOMParser() {
        FlowersDOMBuilder flowersDOMBuilder = new FlowersDOMBuilder();
        flowersDOMBuilder.buildFlowersList(FILE_PATH.toFile().getPath());
        actualFlowerList = flowersDOMBuilder.getFlowers();
        Assert.assertTrue(expectedFlowerList.equals(expectedFlowerList));
    }

    @Test
    public void testStAXParser() {
        FlowerStAXBuilder flowerStAXBuilder = new FlowerStAXBuilder();
        flowerStAXBuilder.buildFlowersList(FILE_PATH.toFile().getPath());
        actualFlowerList = flowerStAXBuilder.getFlowers();
        Assert.assertTrue(expectedFlowerList.equals(expectedFlowerList));
    }
}