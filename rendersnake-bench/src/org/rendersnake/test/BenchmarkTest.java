package org.rendersnake.test;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;

public class BenchmarkTest {
    static final int WARM_PAGES = 1000;
    static final int BENCH_PAGES = 1000000;
	
    public static void main(String[] args) throws Exception {
        BenchmarkTest b = new BenchmarkTest();
        b.warmUp();  
        if (args.length > 0 && args[0].equals("ready")) {
        	System.out.print("Ready?");
        	System.in.read();
        	System.out.println("Running...");
        }
        long ms = System.currentTimeMillis();
        
        b.buildPage(BENCH_PAGES);
        
        System.out.println("Run in " + (System.currentTimeMillis()-ms) + " [ms]");
    }
    
    public void buildPage(int howMany) throws IOException {
        NoWriter sink = new NoWriter();                
        for(int t=0;t<howMany;t++) {
        	HtmlCanvas c = new HtmlCanvas(sink);
            c.render(HomePage.INSTANCE);
        }
        if (howMany != WARM_PAGES)
        	System.out.println("Written chars:" + sink.written);
    }
    
    public void warmUp() throws IOException {
    	this.buildPage(WARM_PAGES);
    }
}
