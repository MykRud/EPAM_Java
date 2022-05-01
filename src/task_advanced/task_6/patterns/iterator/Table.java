package task_advanced.task_6.patterns.iterator;

import java.util.Iterator;

public class Table implements Iterable<String> {
    private String[] columns;
    private int[] rows;

    public Table(String[] columns, int[] rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public Iterator<String> iterator() {
        return new TableIterator(columns, rows);
    }

    class TableIterator implements Iterator<String>{
        private String[] columns;
        private int[] rows;
        private int i;
        private int j;

        public TableIterator(String[] firstArray, int[] secondArray) {
            this.columns = firstArray;
            this.rows = secondArray;
        }

        @Override
        public boolean hasNext() {
            if(i != columns.length)
                return true;
            else
                return false;
        }

        @Override
        public String next() {
            String result = columns[i] + "" + rows[j++];
            if(j == rows.length) {
                i++;
                j = 0;
            }
            return result;
        }
    }
}
