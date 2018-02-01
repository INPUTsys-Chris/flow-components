package com.vaadin.flow.component.board;

import org.junit.Assert;
import org.junit.Test;

public class RowTest {

    @Test
    public void addOne() {
        DummyComponent c1 = new DummyComponent();
        Row row = new Row();
        row.add(c1);
        BoardTest.assertChildren(row, c1);
    }

    @Test
    public void addFour() {
        DummyComponent c1 = new DummyComponent();
        DummyComponent c2 = new DummyComponent();
        DummyComponent c3 = new DummyComponent();
        DummyComponent c4 = new DummyComponent();
        Row row = new Row();
        row.add(c1, c2, c3, c4);
        BoardTest.assertChildren(row, c1, c2, c3, c4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFive() throws Exception {
        DummyComponent c1 = new DummyComponent();
        DummyComponent c2 = new DummyComponent();
        DummyComponent c3 = new DummyComponent();
        DummyComponent c4 = new DummyComponent();
        DummyComponent c5 = new DummyComponent();
        Row row = new Row();
        row.add(c1, c2, c3, c4, c5);
    }

    @Test
    public void remove() throws Exception {
        DummyComponent c1 = new DummyComponent();
        DummyComponent c2 = new DummyComponent();
        DummyComponent c3 = new DummyComponent();
        DummyComponent c4 = new DummyComponent();

        Row row = new Row();
        row.add(c1, c2, c3, c4);

        row.remove(c4);
        BoardTest.assertChildren(row, c1, c2, c3);
        row.remove(c2);
        BoardTest.assertChildren(row, c1, c3);
        row.remove(c1);
        BoardTest.assertChildren(row, c3);
        row.remove(c3);
        BoardTest.assertChildren(row);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeUnrelated() throws Exception {
        DummyComponent c1 = new DummyComponent();

        Row row = new Row();
        row.add(c1);

        row.remove(new DummyComponent());
    }

    @Test
    public void setGetColspan() throws Exception {
        DummyComponent c1 = new DummyComponent();
        DummyComponent c2 = new DummyComponent();
        DummyComponent c3 = new DummyComponent();

        Row row = new Row();

        row.add(c1, 2);
        Assert.assertEquals(2, row.getComponentSpan(c1));

        row.add(c2, c3);
        Assert.assertEquals(2, row.getComponentSpan(c1));
        Assert.assertEquals(1, row.getComponentSpan(c2));
        Assert.assertEquals(1, row.getComponentSpan(c3));

        row.setComponentSpan(c1, 1);
        Assert.assertEquals(1, row.getComponentSpan(c1));
        Assert.assertEquals(1, row.getComponentSpan(c2));
        Assert.assertEquals(1, row.getComponentSpan(c3));

        row.setComponentSpan(c2, 2);
        Assert.assertEquals(1, row.getComponentSpan(c1));
        Assert.assertEquals(2, row.getComponentSpan(c2));
        Assert.assertEquals(1, row.getComponentSpan(c3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setColspanUnrelated() throws Exception {
        new Row().setComponentSpan(new DummyComponent(), 2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getColspanUnrelated() throws Exception {
        new Row().getComponentSpan(new DummyComponent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceedTotalSpanThroughAdd() throws Exception {
        Row row = new Row();
        row.add(new DummyComponent(), 4);
        row.add(new DummyComponent());

    }

    @Test(expected = IllegalArgumentException.class)
    public void exceedTotalSpanThroughSetColspan() throws Exception {
        Row row = new Row();
        DummyComponent dummyComponent = new DummyComponent();
        DummyComponent dummyComponent2 = new DummyComponent();
        row.add(dummyComponent, 3);
        row.add(dummyComponent2);
        row.setComponentSpan(dummyComponent2, 2);

    }

}
