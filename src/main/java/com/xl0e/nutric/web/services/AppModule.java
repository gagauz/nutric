package com.xl0e.nutric.web.services;

import org.apache.tapestry5.ComponentParameterConstants;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.ImportModule;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.FactoryDefaults;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.ExtensibleJavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.web.services.modules.CoreWebappModule;

import com.ivaga.tapestry.csscombiner.CssCombinerModule;
import com.ivaga.tapestry.csscombiner.LessModule;
import com.xl0e.tapestry.hibernate.HibernateModule;

@ImportModule({
        CoreWebappModule.class,
        LessModule.class,
        CssCombinerModule.class,
        HibernateModule.class
})
public class AppModule {

    @FactoryDefaults
    public static void contributeFactoryDefaults(MappedConfiguration<String, Object> configuration) {
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
        configuration.override(SymbolConstants.HMAC_PASSPHRASE, "1.0-SNAPSHOT");
        configuration.override(ComponentParameterConstants.GRID_TABLE_CSS_CLASS, "table table-responsive");
    }

    @ApplicationDefaults
    public static void contributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
        configuration.add(SymbolConstants.FORM_CLIENT_LOGIC_ENABLED, false);
        configuration.add(SymbolConstants.SECURE_ENABLED, true);
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "ru,en");
        configuration.add(SymbolConstants.GZIP_COMPRESSION_ENABLED, "false");
        configuration.add(SymbolConstants.CHARSET, "utf-8");
        configuration.add(SymbolConstants.COMBINE_SCRIPTS, true);
        configuration.add(SymbolConstants.ENABLE_HTML5_SUPPORT, true);
        configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
        // configuration.add(SymbolConstants.BOOTSTRAP_ROOT, "context:/static/bootstrap-3.3.6-dist");
        configuration.add(SymbolConstants.MINIFICATION_ENABLED, true);
    }

    public static void bind(ServiceBinder binder) {
        binder.bind(JavaScriptStack.class, ExtensibleJavaScriptStack.class).withId("AppJavaScriptStack");
        // binder.bind(JSSupport.class);
    }

    // @Decorate(serviceInterface = JavaScriptStackSource.class)
    // public JavaScriptStackSource decorateJavaScriptStackSource(JavaScriptStackSource original) {
    // return new JavaScriptStackSourceWrapper(original);
    // }

    @Contribute(JavaScriptStackSource.class)
    public static void contributeJavaScriptStackSource(MappedConfiguration<String, JavaScriptStack> map,
                                                       AssetSource assetSource,
                                                       @InjectService("AppJavaScriptStack") JavaScriptStack appJavaScriptStack) {
        map.add("app-core", appJavaScriptStack);
    }

}
