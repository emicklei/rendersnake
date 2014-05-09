package org.rendersnake.inner;

public class Canvas {

    public static void main(String[] args) {
        Canvas c = new Canvas();
        c
            .table()
                .tr()
                    .td()
                        .div()
                        ._div()
                    ._td()
               ._tr()
           ._table();
    }

    public Table table() {
        return this.new Table();
    }

    public Canvas div() {
        return this;
    }

    public Canvas _div() {
        return this;
    }

    public Canvas _td() {
        return this;
    }

    public Canvas _tr() {
        return this;
    }    

    public Canvas _table() {
        return this;
    }    
    
    public class Table {
        public TR tr() {
            return this.new TR();
        }
        public class TR {
            public TD td() {
                return this.new TD();
            }
            public class TD extends Canvas {

            }
        }
    }
}
