package org.rendersnake.site.module;

import org.rendershark.core.RendersharkModule;
import org.rendershark.http.HttpServer;
import org.rendersnake.site.DevGuidePageAction;
import org.rendersnake.site.ExamplesJQueryPageAction;
import org.rendersnake.site.ExamplesPageAction;
import org.rendersnake.site.IndexPageAction;
import org.rendersnake.site.MobilePageAction;
import org.rendersnake.site.StatsManager;
import org.rendersnake.site.TranslatorPageAction;
import org.rendersnake.site.example.LoginForm;
import org.rendersnake.site.ipad.AnatomyView;
import org.rendersnake.site.ipad.ButtonsView;
import org.rendersnake.site.ipad.IPadPageAction;
import org.rendersnake.site.ipad.ListView;
import org.rendersnake.site.ipad.WidgetsView;

import com.google.inject.AbstractModule;

public class RendersakeSiteModule extends AbstractModule {
    public static void main(String[] args) {
    	HttpServer.main(args);
    }
    
    public void configure() {
    	install(new RendersharkModule.HTML());
    	bind(IndexPageAction.class);
    	bind(ExamplesPageAction.class);
    	bind(LoginForm.class);
    	bind(DevGuidePageAction.class);
    	bind(TranslatorPageAction.class);
    	bind(ExamplesJQueryPageAction.class);
    	bind(MobilePageAction.class);
    	bind(IPadPageAction.class);
    	bind(ListView.class);
    	bind(WidgetsView.class);
    	bind(ButtonsView.class);
    	bind(AnatomyView.class);
    	bind(StatsManager.class);
    }
}
