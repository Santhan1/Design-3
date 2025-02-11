import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q;

    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flattenList(nestedList);
        // return
    }

    @Override
    public Integer next() {
        return q.poll();

    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();

    }

    private void flattenList(List<NestedInteger> nestedList) {
        for (NestedInteger ele : nestedList) {
            if (ele.isInteger()) {
                q.add(ele.getInteger());

            } else {
                flattenList(ele.getList());

            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
