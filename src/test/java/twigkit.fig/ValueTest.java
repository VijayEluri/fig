package twigkit.fig;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mr.olafsson
 */
public class ValueTest {

    @Test
    public void createValue() {
        Value value = new Value();
        Assert.assertEquals("", value.label());
        Assert.assertNull(value.get());
        Assert.assertFalse(value.required());
    }

    @Test
    public void fluentCreation() {
        Value value = new Value().label("attr1").set("myValue");
        Assert.assertEquals("attr1", value.label());
        Assert.assertEquals("myValue", value.get());
        Assert.assertFalse(value.required());

        value.require();
        Assert.assertTrue(value.required());
    }

    @Test
    public void testTypes() {
        Value v = new Value("label", "100");
        Assert.assertEquals("100", v.as_string());
        Assert.assertEquals("100", v.toString());
        Assert.assertEquals(100, v.as_int());
        
        v.set(100);
        Assert.assertEquals(100, v.as_int());
        Assert.assertEquals("100", v.as_string());

        v.set(true);
        Assert.assertEquals(true, v.as_boolean());
        Assert.assertEquals("true", v.as_string());
    }
    
    @Test
    public void testEquality() {
        Value v1 = new Value().label("attr1").set("myValue");
        Value v2 = new Value().label("attr1").set("myValue");
        Value v3 = new Value().label("attr1").set("different");

        Assert.assertEquals(v1, v2);
        Assert.assertFalse(v1.equals(v3));

        Assert.assertEquals(v1.hashCode(), v2.hashCode());
        Assert.assertFalse(v1.hashCode() == v3.hashCode());
    }
}
