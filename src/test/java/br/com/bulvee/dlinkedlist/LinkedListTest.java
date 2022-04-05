package br.com.bulvee.dlinkedlist;

import br.com.bulvee.dlinkedlist.util.Mock;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


class LinkedListTest extends Mock {

    private LinkedList<Stock> linkedList;


    @BeforeEach
    void setUp() {
        linkedList = new LinkedList();
//        mock = new Mock();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenItems_whenAddFirst_ValidPosition_thenVerify(TestInfo testInfo) {
        System.out.println("displayName = " + testInfo.getDisplayName());
        linkedList.addFirst(petr4);
        linkedList.addFirst(b3sa3);
        linkedList.addFirst(bbas3);
        linkedList.addFirst(vale3);

        System.out.println(linkedList);

        assertTrue(isAtIndex(1, bbas3));
        assertTrue(isIndex(0, vale3));
    }


    @Test
    void givenItems_whenAddLast_ValidPosition_thenVerify(TestInfo testInfo) {
        System.out.println("displayName = " + testInfo.getDisplayName());
        linkedList.addFirst(b3sa3);
        linkedList.addFirst(bbas3);
        linkedList.addFirst(vale3);
        //LastItem
        linkedList.addLast(petr4);

        System.out.println(linkedList);
        assertTrue(validPositionLast(petr4));
    }

    @Test
    void givenIndex_whenGetInvalidIndex_thenVerify(TestInfo testInfo) {
        System.out.println("displayName = " + testInfo.getDisplayName());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            linkedList.get(1000);
        });
    }

    @Test
    void givenIndex_whenAddAtMiddle_thenVerify(TestInfo testInfo) {
        System.out.println("displayName = " + testInfo.getDisplayName());

        linkedList.addFirst(b3sa3);
        linkedList.addFirst(bbas3);
        linkedList.addFirst(vale3);


        Stock atMiddle = irbr3;
        int index = 1;
        linkedList.add(index, atMiddle);

        System.out.println(linkedList);
        assertTrue(isAtIndex(index, atMiddle));
    }

    @Test
    void givenItems_whenAddAtFirst_checkSize_thenVerify(TestInfo testInfo) {
        System.out.println("di" +
                "splayName = " + testInfo.getDisplayName());

        linkedList.addFirst(b3sa3);
        linkedList.addFirst(bbas3);
        linkedList.addFirst(vale3);

        assertTrue(isSize(3));

    }

    private boolean isSize(int i) {
        return linkedList.size() == i;
    }


    @Test
    void givenItems_whenAdd_checkIndex_thenVerify(TestInfo testInfo) {
        System.out.println("displayName = " + testInfo.getDisplayName());

        linkedList.add(b3sa3);
        linkedList.add(bbas3);
        linkedList.add(vale3);

        System.out.println(linkedList);

        assertTrue(isIndex(2, vale3));
    }

    @Test
    void givenItems_whenRemove_checkRemoved_thenVerify(TestInfo testInfo) {
        System.out.println("displayName = " + testInfo.getDisplayName());

        linkedList.add(b3sa3);
        linkedList.add(bbas3);
        linkedList.add(vale3);
        linkedList.add(irbr3);

        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);

        Assertions.assertFalse(isIndex(2, vale3));
    }

    @Test
    void givenItems_whenRemoveFirst_checkRemoved_thenVerify(TestInfo testInfo) {
        System.out.println("displayName = " + testInfo.getDisplayName());

        linkedList.add(b3sa3);
        linkedList.add(bbas3);
        linkedList.add(vale3);
        linkedList.add(irbr3);

        linkedList.removeFirst();

        Assertions.assertFalse(isIndex(0, b3sa3));
        Assertions.assertTrue(isIndex(0, bbas3));

    }

    @Test
    void givenItems_whenAddNewItemAtIndex_checkIndex_thenVerify(TestInfo testInfo) {
        System.out.println("displayName = " + testInfo.getDisplayName());

        linkedList.add(b3sa3);
        linkedList.add(bbas3);
        //2 - petr4
        linkedList.add(vale3);
        linkedList.add(irbr3);

        linkedList.add(2, petr4);

        System.out.println(linkedList);
        Assertions.assertTrue(isIndex(1, bbas3));
        Assertions.assertTrue(isIndex(2, petr4));
        Assertions.assertTrue(isIndex(3, vale3));
    }

    private boolean isIndex(int index, Stock item) {
        Stock stock = linkedList.get(index);
        return stock.equals(item);
    }

    private boolean isAtIndex(int i, Object item) {
        Stock stock = linkedList.get(1);
        return stock.equals(item);
    }


    private boolean validPositionLast(Object item) {
        Stock lastItem = linkedList.get(linkedList.size() - 1);

        return lastItem.equals(item);
    }

}